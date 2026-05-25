package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.response.FavoritoResponseDTO;
import com.example.proyecto_mealplanner.entity.Favorito;
import com.example.proyecto_mealplanner.entity.Receta;
import com.example.proyecto_mealplanner.entity.Usuario;
import com.example.proyecto_mealplanner.exception.DuplicateResourceException;
import com.example.proyecto_mealplanner.exception.ResourceNotFoundException;
import com.example.proyecto_mealplanner.mapper.FavoritoMapper;
import com.example.proyecto_mealplanner.repository.FavoritoRepository;
import com.example.proyecto_mealplanner.repository.RecetaRepository;
import com.example.proyecto_mealplanner.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final RecetaRepository recetaRepository;
    private final UsuarioRepository usuarioRepository;
    private final FavoritoMapper favoritoMapper;

    public FavoritoResponseDTO agregarFavorito(Long recetaId) {
        Usuario usuario = obtenerUsuarioActual();
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));
        if (favoritoRepository.existsByUsuarioAndReceta(usuario, receta)) {
            throw new DuplicateResourceException("Ya está en favoritos");
        }
        Favorito favorito = Favorito.builder()
                .usuario(usuario)
                .receta(receta)
                .build();
        return favoritoMapper.toDto(favoritoRepository.save(favorito));
    }

    public void eliminarFavorito(Long recetaId) {
        Usuario usuario = obtenerUsuarioActual();
        Favorito favorito = favoritoRepository.findByUsuarioAndRecetaIdReceta(usuario, recetaId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito no encontrado"));
        favoritoRepository.delete(favorito);
    }

    private Usuario obtenerUsuarioActual() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}