package com.example.proyecto_mealplanner.dto.request;

import com.example.proyecto_mealplanner.enums.CategoriaIngrediente;

import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class IngredienteRequestDTO {

    @NotBlank
    private String nombre;

    private CategoriaIngrediente categoria;
}