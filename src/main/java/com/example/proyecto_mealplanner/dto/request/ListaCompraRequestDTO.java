package com.example.proyecto_mealplanner.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ListaCompraRequestDTO {

    private String nombreIngrediente;

    private Integer cantidad;

    private Long usuarioId;
}