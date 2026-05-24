package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}