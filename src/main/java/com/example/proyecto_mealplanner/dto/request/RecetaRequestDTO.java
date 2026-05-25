package com.example.proyecto_mealplanner.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaRequestDTO {
    @NotBlank
    private String titulo;
    private Integer tiempoPreparacion;
    private Integer porciones;
    private boolean publica = true;

    @NotNull
    private List<PasoRequestDTO> pasos;

    @NotNull
    private List<RecetaIngredienteRequestDTO> ingredientes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PasoRequestDTO {
        private Integer orden;
        private String descripcion;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecetaIngredienteRequestDTO {
        private Long ingredienteId;
        private Double cantidad;
        private String unidad; // Debe coincidir con el enum UnidadMedida
    }
}
