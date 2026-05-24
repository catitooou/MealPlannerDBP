package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.RecetaRequestDTO;
import com.example.proyecto_mealplanner.dto.response.RecetaResponseDTO;
import com.example.proyecto_mealplanner.model.Receta;
import com.example.proyecto_mealplanner.repository.RecetaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaResponseDTO crearReceta(RecetaRequestDTO dto) {

        Receta receta = Receta.builder()
                .titulo(dto.getTitulo())
                .pasos(dto.getPasos())
                .build();

        recetaRepository.save(receta);

        return RecetaResponseDTO.builder()
                .idReceta(receta.getIdReceta())
                .titulo(receta.getTitulo())
                .pasos(receta.getPasos())
                .build();
    }

    public List<RecetaResponseDTO> obtenerRecetas() {

        return recetaRepository.findAll()
                .stream()
                .map(receta -> RecetaResponseDTO.builder()
                        .idReceta(receta.getIdReceta())
                        .titulo(receta.getTitulo())
                        .pasos(receta.getPasos())
                        .build())
                .toList();
    }
}