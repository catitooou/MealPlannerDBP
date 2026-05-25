package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.response.FavoritoResponseDTO;
import com.example.proyecto_mealplanner.entity.Favorito;
import com.example.proyecto_mealplanner.entity.Receta;
import com.example.proyecto_mealplanner.entity.Usuario;
import com.example.proyecto_mealplanner.exception.DuplicateResourceException;
import com.example.proyecto_mealplanner.exception.ResourceNotFoundException;
import com.example.proyecto_mealplanner.mapper.FavoritoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final RecetaRepository recetaRepository;
    private final UsuarioRepository usuarioRepository;
    private final FavoritoMapper favoritoMapper;

    @Repository
    public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
        boolean existsByUsuarioAndReceta(Usuario usuario, Receta receta);
        Optional<Favorito> findByUsuarioAndRecetaIdReceta(Usuario usuario, Long recetaId);
    }

    @Repository
    public interface RecetaRepository extends JpaRepository<Receta, Long> {
        // ...
    }

    @Repository
    public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        Optional<Usuario> findByEmail(String email);
        boolean existsByEmail(String email);
    }

    @Transactional
    public FavoritoResponseDTO agregarFavorito(Long recetaId) {
        Usuario usuario = obtenerUsuarioActual();
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: " + recetaId));

        if (favoritoRepository.existsByUsuarioAndReceta(usuario, receta)) {
            throw new DuplicateResourceException("Ya tienes esta receta en favoritos");
        }

        Favorito favorito = Favorito.builder()
                .usuario(usuario)
                .receta(receta)
                .build();

        return favoritoMapper.toDto(favoritoRepository.save(favorito));
    }

    @Transactional
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