package com.example.proyecto_mealplanner.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VideoRequestDTO {

    private String nombreArchivo;

    private String urlVideo;
}