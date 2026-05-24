package com.example.proyecto_mealplanner.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}