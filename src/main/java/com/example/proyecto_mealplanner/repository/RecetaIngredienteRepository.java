package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Long> {
}