package com.example.proyecto_mealplanner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponseDTO {
    private Long idVideo;
    private String nombreArchivo;
    private String urlS3;
    private String estado;
    private Long transcripcionId;
    private Long recetaGeneradaId;
}
