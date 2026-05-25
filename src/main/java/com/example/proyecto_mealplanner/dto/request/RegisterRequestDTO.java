package com.example.proyecto_mealplanner.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank @Size(min = 2, max = 100)
    private String nombre;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 6)
    private String password;
}
