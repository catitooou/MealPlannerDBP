package com.example.proyecto_mealplanner.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListaCompraRequestDTO {
    @NotBlank
    private String nombre;
    private List<Long> recetasIds; // IDs de recetas de las que generar la lista
}
