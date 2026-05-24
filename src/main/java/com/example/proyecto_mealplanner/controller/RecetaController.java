package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.RecetaRequestDTO;
import com.example.proyecto_mealplanner.dto.response.RecetaResponseDTO;
import com.example.proyecto_mealplanner.service.RecetaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor

public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping
    public ResponseEntity<RecetaResponseDTO> crearReceta(
            @Valid @RequestBody RecetaRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recetaService.crearReceta(dto));
    }

    @GetMapping
    public ResponseEntity<List<RecetaResponseDTO>> obtenerRecetas() {

        return ResponseEntity.ok(recetaService.obtenerRecetas());
    }
}