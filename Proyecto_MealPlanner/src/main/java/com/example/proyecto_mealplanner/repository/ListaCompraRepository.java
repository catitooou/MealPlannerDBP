package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.model.ListaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long> {
}