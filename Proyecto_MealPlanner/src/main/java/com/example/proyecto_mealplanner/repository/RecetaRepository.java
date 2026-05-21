package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByTituloContainingIgnoreCase(String titulo);

}