package com.example.proyecto_mealplanner.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthResponseDTO {

    private String token;

    private String mensaje;
}