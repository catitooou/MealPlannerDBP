package com.example.proyecto_mealplanner.dto.response;

import com.example.proyecto_mealplanner.enums.CategoriaIngrediente;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class IngredienteResponseDTO {

    private Long idIngrediente;

    private String nombre;

    private CategoriaIngrediente categoria;
}