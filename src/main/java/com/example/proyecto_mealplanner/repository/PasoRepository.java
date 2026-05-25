package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.entity.Paso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PasoRepository extends JpaRepository<Paso, Long> {
    List<Paso> findByRecetaIdRecetaOrderByOrdenAsc(Long recetaId);
    void deleteByRecetaIdReceta(Long recetaId);
}