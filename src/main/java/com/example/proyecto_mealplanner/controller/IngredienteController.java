package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.IngredienteRequestDTO;
import com.example.proyecto_mealplanner.dto.response.IngredienteResponseDTO;
import com.example.proyecto_mealplanner.service.IngredienteService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingredientes")
@RequiredArgsConstructor

public class IngredienteController {

    private final IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<IngredienteResponseDTO> crearIngrediente(
            @Valid @RequestBody IngredienteRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ingredienteService.crearIngrediente(dto));
    }
}