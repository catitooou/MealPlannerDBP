package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.Transcripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscripcionRepository extends JpaRepository<Transcripcion, Long> {
}