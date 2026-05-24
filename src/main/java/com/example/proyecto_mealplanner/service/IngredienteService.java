package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.IngredienteRequestDTO;
import com.example.proyecto_mealplanner.dto.response.IngredienteResponseDTO;
import com.example.proyecto_mealplanner.model.Ingrediente;
import com.example.proyecto_mealplanner.repository.IngredienteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteResponseDTO crearIngrediente(IngredienteRequestDTO dto) {

        Ingrediente ingrediente = Ingrediente.builder()
                .nombre(dto.getNombre())
                .categoria(dto.getCategoria())
                .build();

        ingredienteRepository.save(ingrediente);

        return IngredienteResponseDTO.builder()
                .idIngrediente(ingrediente.getIdIngrediente())
                .nombre(ingrediente.getNombre())
                .categoria(ingrediente.getCategoria())
                .build();
    }
}