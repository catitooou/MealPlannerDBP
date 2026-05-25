package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.IngredienteRequestDTO;
import com.example.proyecto_mealplanner.dto.response.IngredienteResponseDTO;
import com.example.proyecto_mealplanner.entity.Ingrediente;
import com.example.proyecto_mealplanner.enums.CategoriaIngrediente;
import com.example.proyecto_mealplanner.exception.DuplicateResourceException;
import com.example.proyecto_mealplanner.exception.ResourceNotFoundException;
import com.example.proyecto_mealplanner.mapper.IngredienteMapper;
import com.example.proyecto_mealplanner.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final IngredienteMapper ingredienteMapper;

    /**
     * Lista todos los ingredientes con paginación.
     */
    @Transactional(readOnly = true)
    public Page<IngredienteResponseDTO> listarIngredientes(Pageable pageable) {
        return ingredienteRepository.findAll(pageable)
                .map(ingredienteMapper::toDto);
    }

    /**
     * Obtiene un ingrediente por su ID.
     */
    @Transactional(readOnly = true)
    public IngredienteResponseDTO obtenerIngredientePorId(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado con id: " + id));
        return ingredienteMapper.toDto(ingrediente);
    }

    /**
     * Crea un nuevo ingrediente.
     */
    @Transactional
    public IngredienteResponseDTO crearIngrediente(IngredienteRequestDTO request) {
        // Verificar si ya existe un ingrediente con el mismo nombre (sin distinción de mayúsculas)
        if (ingredienteRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new DuplicateResourceException("Ya existe un ingrediente con el nombre: " + request.getNombre());
        }

        Ingrediente ingrediente = ingredienteMapper.toEntity(request);
        // Convertir el string de categoria a Enum
        ingrediente.setCategoria(CategoriaIngrediente.valueOf(request.getCategoria().toUpperCase()));

        Ingrediente saved = ingredienteRepository.save(ingrediente);
        return ingredienteMapper.toDto(saved);
    }

    /**
     * Actualiza un ingrediente existente.
     */
    @Transactional
    public IngredienteResponseDTO actualizarIngrediente(Long id, IngredienteRequestDTO request) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado con id: " + id));

        // Si el nombre cambió, verificar que no exista otro con el mismo nombre
        if (!ingrediente.getNombre().equalsIgnoreCase(request.getNombre()) &&
                ingredienteRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new DuplicateResourceException("Ya existe un ingrediente con el nombre: " + request.getNombre());
        }

        ingrediente.setNombre(request.getNombre());
        ingrediente.setCategoria(CategoriaIngrediente.valueOf(request.getCategoria().toUpperCase()));

        Ingrediente updated = ingredienteRepository.save(ingrediente);
        return ingredienteMapper.toDto(updated);
    }

    /**
     * Elimina un ingrediente (solo si no está referenciado en ninguna receta).
     * En un sistema real, se puede añadir una validación de integridad referencial.
     */
    @Transactional
    public void eliminarIngrediente(Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingrediente no encontrado con id: " + id);
        }
        // Opcional: verificar si el ingrediente está siendo usado antes de eliminar
        // if (recetaIngredienteRepository.existsByIngredienteId(id)) {
        //     throw new InvalidOperationException("No se puede eliminar el ingrediente porque está siendo usado en recetas");
        // }
        ingredienteRepository.deleteById(id);
    }

    /**
     * Busca ingredientes cuyo nombre contenga la cadena (ignorando mayúsculas).
     * Útil para autocompletado.
     */
    @Transactional(readOnly = true)
    public Page<IngredienteResponseDTO> buscarPorNombre(String nombre, Pageable pageable) {
        return ingredienteRepository.findByNombreContainingIgnoreCase(nombre, pageable)
                .map(ingredienteMapper::toDto);
    }
}