package com.example.proyecto_mealplanner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListaCompraResponseDTO {
    private Long idLista;
    private String nombre;
    private Long usuarioId;
    private LocalDateTime fechaCreacion;
    private List<ListaCompraItemDTO> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListaCompraItemDTO {
        private Long idItem;
        private Long ingredienteId;
        private String nombreIngrediente;
        private Double cantidad;
        private String unidad;
        private boolean marcado;
    }
}
