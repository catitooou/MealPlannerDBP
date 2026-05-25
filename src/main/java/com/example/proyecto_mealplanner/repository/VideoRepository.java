package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.entity.Video;
import com.example.proyecto_mealplanner.enums.EstadoVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    /**
     * Busca videos por su estado (pendiente, procesando, completado, error).
     */
    List<Video> findByEstado(EstadoVideo estado);

    /**
     * Busca videos de un usuario específico.
     */
    Page<Video> findByUsuarioIdUsuario(Long usuarioId, Pageable pageable);

    /**
     * Obtiene un video junto con su transcripción y receta generada (para evitar N+1).
     * Usar @EntityGraph en el servicio si es necesario, pero este método es útil.
     */
    Optional<Video> findByIdWithDetails(Long id);
}