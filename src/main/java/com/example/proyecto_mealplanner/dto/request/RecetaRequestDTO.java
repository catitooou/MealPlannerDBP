package com.example.proyecto_mealplanner.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RecetaRequestDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String pasos;
}