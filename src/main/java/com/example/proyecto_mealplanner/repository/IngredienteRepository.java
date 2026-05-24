package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}