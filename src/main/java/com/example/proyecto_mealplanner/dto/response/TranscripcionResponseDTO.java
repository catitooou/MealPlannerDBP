package com.example.proyecto_mealplanner.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TranscripcionResponseDTO {

    private Long idTranscripcion;

    private String contenido;
}