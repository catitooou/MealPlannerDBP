package com.example.proyecto_mealplanner.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ListaCompraResponseDTO {

    private Long idLista;

    private String nombreIngrediente;

    private Integer cantidad;
}