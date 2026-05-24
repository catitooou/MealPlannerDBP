package com.example.proyecto_mealplanner.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioResponseDTO {

    private Long idUsuario;

    private String nombre;

    private String email;
}