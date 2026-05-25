package com.example.proyecto_mealplanner.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteRequestDTO {
    @NotBlank
    private String nombre;
    private String categoria; // PROTEINA, CARBOHIDRATO, etc.
}
