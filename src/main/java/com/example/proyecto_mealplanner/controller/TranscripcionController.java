package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.TranscripcionRequestDTO;
import com.example.proyecto_mealplanner.dto.response.TranscripcionResponseDTO;
import com.example.proyecto_mealplanner.service.TranscripcionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transcripciones")
@RequiredArgsConstructor

public class TranscripcionController {

    private final TranscripcionService transcripcionService;

    @PostMapping
    public ResponseEntity<TranscripcionResponseDTO> guardarTranscripcion(
            @RequestBody TranscripcionRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transcripcionService.guardarTranscripcion(dto));
    }
}