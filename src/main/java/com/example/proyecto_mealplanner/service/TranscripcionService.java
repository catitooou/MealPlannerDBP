package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.TranscripcionRequestDTO;
import com.example.proyecto_mealplanner.dto.response.TranscripcionResponseDTO;
import com.example.proyecto_mealplanner.model.Transcripcion;
import com.example.proyecto_mealplanner.repository.TranscripcionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TranscripcionService {

    private final TranscripcionRepository transcripcionRepository;

    public TranscripcionResponseDTO guardarTranscripcion(TranscripcionRequestDTO dto) {

        Transcripcion transcripcion = Transcripcion.builder()
                .contenido(dto.getContenido())
                .build();

        transcripcionRepository.save(transcripcion);

        return TranscripcionResponseDTO.builder()
                .idTranscripcion(transcripcion.getIdTranscripcion())
                .contenido(transcripcion.getContenido())
                .build();
    }
}