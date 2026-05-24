package com.example.proyecto_mealplanner.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterRequestDTO {

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    private String password;
}