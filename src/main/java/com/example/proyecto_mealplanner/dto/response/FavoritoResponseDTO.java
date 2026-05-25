package com.example.proyecto_mealplanner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoResponseDTO {
    private Long idFavorito;
    private Long recetaId;
    private String tituloReceta;
    private Long usuarioId;
}
