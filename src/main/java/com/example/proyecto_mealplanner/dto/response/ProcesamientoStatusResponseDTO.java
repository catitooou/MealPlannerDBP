package com.example.proyecto_mealplanner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcesamientoStatusResponseDTO {
    private String taskId;
    private String estado; // PENDING, COMPLETED, ERROR
    private String mensaje;
    private Long recetaId; // si ya se generó
}
