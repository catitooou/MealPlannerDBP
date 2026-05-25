package com.example.proyecto_mealplanner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaResponseDTO {
    private Long idReceta;
    private String titulo;
    private Integer tiempoPreparacion;
    private Integer porciones;
    private boolean publica;
    private Long usuarioId;
    private String nombreUsuario;
    private List<PasoDTO> pasos;
    private List<IngredienteCantidadDTO> ingredientes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PasoDTO {
        private Long idPaso;
        private Integer orden;
        private String descripcion;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IngredienteCantidadDTO {
        private Long ingredienteId;
        private String nombreIngrediente;
        private Double cantidad;
        private String unidad;
        private String categoria;
    }
}
