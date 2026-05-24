package com.example.proyecto_mealplanner.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FavoritoRequestDTO {

    private Long usuarioId;

    private Long recetaId;
}