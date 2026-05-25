package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.RecetaRequestDTO;
import com.example.proyecto_mealplanner.dto.response.RecetaResponseDTO;
import com.example.proyecto_mealplanner.entity.*;
import com.example.proyecto_mealplanner.enums.UnidadMedida;
import com.example.proyecto_mealplanner.exception.ResourceNotFoundException;
import com.example.proyecto_mealplanner.mapper.RecetaMapper;
import com.example.proyecto_mealplanner.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final UsuarioRepository usuarioRepository;
    private final IngredienteRepository ingredienteRepository;
    private final RecetaMapper recetaMapper;
    private final PasoRepository pasoRepository;
    private final RecetaIngredienteRepository recetaIngredienteRepository;

    @Transactional(readOnly = true)
    public Page<RecetaResponseDTO> listarRecetasPublicas(Pageable pageable) {
        return recetaRepository.findByPublicaTrue(pageable)
                .map(recetaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public RecetaResponseDTO obtenerRecetaPorId(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));
        return recetaMapper.toDto(receta);
    }

    @Transactional
    public RecetaResponseDTO crearReceta(RecetaRequestDTO request) {
        Usuario usuarioActual = obtenerUsuarioActual();
        Receta receta = recetaMapper.toEntity(request);
        receta.setUsuario(usuarioActual);
        receta = recetaRepository.save(receta);

        // Guardar pasos
        List<Paso> pasos = new ArrayList<>();
        for (RecetaRequestDTO.PasoRequestDTO pasoDto : request.getPasos()) {
            Paso paso = Paso.builder()
                    .orden(pasoDto.getOrden())
                    .descripcion(pasoDto.getDescripcion())
                    .receta(receta)
                    .build();
            pasos.add(pasoRepository.save(paso));
        }
        receta.setPasos(pasos);

        // Guardar ingredientes
        List<RecetaIngrediente> ingredientes = new ArrayList<>();
        for (RecetaRequestDTO.RecetaIngredienteRequestDTO ingDto : request.getIngredientes()) {
            Ingrediente ing = ingredienteRepository.findById(ingDto.getIngredienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));
            RecetaIngrediente ri = RecetaIngrediente.builder()
                    .receta(receta)
                    .ingrediente(ing)
                    .cantidad(ingDto.getCantidad())
                    .unidad(UnidadMedida.valueOf(ingDto.getUnidad()))
                    .build();
            ingredientes.add(recetaIngredienteRepository.save(ri));
        }
        receta.setRecetaIngredientes(ingredientes);

        return recetaMapper.toDto(receta);
    }

    @Transactional
    public RecetaResponseDTO actualizarReceta(Long id, RecetaRequestDTO request) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));
        if (!receta.getUsuario().getIdUsuario().equals(obtenerUsuarioActual().getIdUsuario())) {
            throw new AccessDeniedException("No tienes permiso para editar esta receta");
        }
        receta.setTitulo(request.getTitulo());
        receta.setTiempoPreparacion(request.getTiempoPreparacion());
        receta.setPorciones(request.getPorciones());
        receta.setPublica(request.isPublica());

        // Actualizar pasos (borrar y crear)
        pasoRepository.deleteByRecetaIdReceta(id);
        List<Paso> nuevosPasos = request.getPasos().stream()
                .map(p -> Paso.builder()
                        .orden(p.getOrden())
                        .descripcion(p.getDescripcion())
                        .receta(receta)
                        .build())
                .collect(Collectors.toList());
        pasoRepository.saveAll(nuevosPasos);
        receta.setPasos(nuevosPasos);

        // Actualizar ingredientes (borrar y crear)
        recetaIngredienteRepository.deleteByRecetaIdReceta(id);
        List<RecetaIngrediente> nuevosIngredientes = request.getIngredientes().stream()
                .map(ing -> {
                    Ingrediente i = ingredienteRepository.findById(ing.getIngredienteId())
                            .orElseThrow();
                    return RecetaIngrediente.builder()
                            .receta(receta)
                            .ingrediente(i)
                            .cantidad(ing.getCantidad())
                            .unidad(UnidadMedida.valueOf(ing.getUnidad()))
                            .build();
                })
                .collect(Collectors.toList());
        recetaIngredienteRepository.saveAll(nuevosIngredientes);
        receta.setRecetaIngredientes(nuevosIngredientes);

        return recetaMapper.toDto(recetaRepository.save(receta));
    }
    @Transactional
    public Receta save(Receta receta) {
        return recetaRepository.save(receta);
    }

    @Transactional
    public void eliminarReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));
        if (!receta.getUsuario().getIdUsuario().equals(obtenerUsuarioActual().getIdUsuario())) {
            throw new AccessDeniedException("No tienes permiso para eliminar esta receta");
        }
        recetaRepository.delete(receta);
    }

    public boolean esPropietario(Long recetaId, Long userId) {
        return recetaRepository.findById(recetaId)
                .map(receta -> receta.getUsuario().getIdUsuario().equals(userId))
                .orElse(false);
    }

    private Usuario obtenerUsuarioActual() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}