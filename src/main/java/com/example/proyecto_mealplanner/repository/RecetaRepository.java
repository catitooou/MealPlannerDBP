package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.entity.Receta;
import com.example.proyecto_mealplanner.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    // -----------------------------------------------------------------
    // Métodos básicos derivados por nomenclatura
    // -----------------------------------------------------------------

    /**
     * Busca recetas públicas con paginación.
     */
    Page<Receta> findByPublicaTrue(Pageable pageable);

    /**
     * Busca recetas de un usuario específico.
     */
    Page<Receta> findByUsuario(Usuario usuario, Pageable pageable);

    /**
     * Busca recetas públicas que contengan una palabra en el título.
     */
    Page<Receta> findByPublicaTrueAndTituloContainingIgnoreCase(String titulo, Pageable pageable);

    /**
     * Obtiene todas las recetas favoritas de un usuario (usando la tabla favoritos).
     */
    @Query("SELECT r FROM Receta r WHERE r.idReceta IN (SELECT f.receta.idReceta FROM Favorito f WHERE f.usuario = :usuario)")
    Page<Receta> findFavoritasByUsuario(@Param("usuario") Usuario usuario, Pageable pageable);

    /**
     * Verifica si una receta pertenece a un usuario (para autorización).
     */
    boolean existsByIdRecetaAndUsuario(Long idReceta, Usuario usuario);

    /**
     * Busca recetas por una lista de IDs (para generar listas de compra o sugerencias).
     */
    List<Receta> findByIdRecetaIn(List<Long> ids);

    // -----------------------------------------------------------------
    // Métodos útiles adicionales
    // -----------------------------------------------------------------

    /**
     * Obtiene una receta junto con sus pasos e ingredientes (evita N+1).
     * Se puede usar con @EntityGraph en el servicio si se prefiere.
     */
    @Query("SELECT DISTINCT r FROM Receta r " +
            "LEFT JOIN FETCH r.pasos " +
            "LEFT JOIN FETCH r.recetaIngredientes ri " +
            "LEFT JOIN FETCH ri.ingrediente " +
            "WHERE r.idReceta = :id")
    Optional<Receta> findByIdWithDetails(@Param("id") Long id);
}