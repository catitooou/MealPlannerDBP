package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.entity.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Long> {

    /**
     * Busca todos los ingredientes asociados a una receta específica.
     * @param recetaId ID de la receta
     * @return Lista de RecetaIngrediente
     */
    List<RecetaIngrediente> findByRecetaIdReceta(Long recetaId);

    /**
     * Busca una relación específica entre una receta y un ingrediente.
     * @param recetaId ID de la receta
     * @param ingredienteId ID del ingrediente
     * @return Optional con la relación si existe
     */
    Optional<RecetaIngrediente> findByRecetaIdRecetaAndIngredienteIdIngrediente(Long recetaId, Long ingredienteId);

    /**
     * Elimina todas las relaciones de ingredientes de una receta (útil al actualizar la lista completa).
     * @param recetaId ID de la receta
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM RecetaIngrediente ri WHERE ri.receta.idReceta = :recetaId")
    void deleteByRecetaIdReceta(@Param("recetaId") Long recetaId);

    /**
     * Verifica si una receta ya contiene un ingrediente específico.
     * @param recetaId ID de la receta
     * @param ingredienteId ID del ingrediente
     * @return true si existe la relación
     */
    boolean existsByRecetaIdRecetaAndIngredienteIdIngrediente(Long recetaId, Long ingredienteId);
}
