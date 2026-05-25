package com.example.proyecto_mealplanner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteResponseDTO {
    private Long idIngrediente;
    private String nombre;
    private String categoria;
}
