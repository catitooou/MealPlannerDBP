package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
}