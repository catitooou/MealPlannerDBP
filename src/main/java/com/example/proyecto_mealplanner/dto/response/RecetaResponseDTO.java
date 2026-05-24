package com.example.proyecto_mealplanner.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RecetaResponseDTO {

    private Long idReceta;

    private String titulo;

    private String pasos;
}