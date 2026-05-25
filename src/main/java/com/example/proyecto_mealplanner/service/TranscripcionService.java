package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.TranscripcionRequestDTO;
import com.example.proyecto_mealplanner.dto.response.TranscripcionResponseDTO;
import com.example.proyecto_mealplanner.entity.Transcripcion;
import com.example.proyecto_mealplanner.repository.TranscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranscripcionService {
    private final TranscripcionRepository transcripcionRepository;

    public TranscripcionResponseDTO guardarTranscripcion(TranscripcionRequestDTO request) {
        Transcripcion t = Transcripcion.builder()
                .contenido(request.getContenido())
                .build();
        t = transcripcionRepository.save(t);
        return new TranscripcionResponseDTO(t.getIdTranscripcion(), t.getContenido());
    }
}
