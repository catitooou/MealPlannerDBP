package com.example.proyecto_mealplanner.dto.response;

import com.example.proyecto_mealplanner.enums.EstadoVideo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VideoResponseDTO {

    private Long idVideo;

    private String nombreArchivo;

    private EstadoVideo estado;
}