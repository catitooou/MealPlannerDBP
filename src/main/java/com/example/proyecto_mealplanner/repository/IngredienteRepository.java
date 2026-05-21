package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    Optional<Ingrediente> findByNombre(String nombre);

}