package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.entity.Favorito;
import com.example.proyecto_mealplanner.entity.Receta;
import com.example.proyecto_mealplanner.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    /**
     * Verifica si un usuario ya tiene marcada una receta como favorita.
     */
    boolean existsByUsuarioAndReceta(Usuario usuario, Receta receta);

    /**
     * Busca un favorito por usuario y por ID de receta (útil para eliminar).
     */
    Optional<Favorito> findByUsuarioAndRecetaIdReceta(Usuario usuario, Long recetaId);

    /**
     * Lista todos los favoritos de un usuario (útil para mostrar su lista de recetas favoritas).
     */
    List<Favorito> findByUsuario(Usuario usuario);

    /**
     * Elimina todos los favoritos de una receta (cuando se elimina la receta).
     */
    void deleteByReceta(Receta receta);
}