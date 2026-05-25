package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.entity.Ingrediente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    /**
     * Verifica si ya existe un ingrediente con el mismo nombre (sin distinción de mayúsculas/minúsculas).
     * @param nombre nombre del ingrediente a comprobar
     * @return true si existe, false en caso contrario
     */
    boolean existsByNombreIgnoreCase(String nombre);

    /**
     * Busca ingredientes cuyo nombre contenga la cadena de búsqueda (ignorando mayúsculas/minúsculas).
     * Útil para autocompletado y búsqueda.
     * @param nombre cadena a buscar
     * @param pageable paginación
     * @return página de ingredientes que coinciden
     */
    Page<Ingrediente> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}