package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}