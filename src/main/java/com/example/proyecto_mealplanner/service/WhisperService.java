package com.example.proyecto_mealplanner.service;

import org.springframework.stereotype.Service;

@Service
public class WhisperService {
    // Simula la llamada a la API de Whisper
    public String transcribirAudio(String urlVideo) {
        // En un caso real, aquí se llamaría a la API de OpenAI
        // Para el MVP, devolvemos un texto de ejemplo
        return """
                Ingredientes:
                - 200g de pasta
                - 2 dientes de ajo
                - Aceite de oliva
                - Sal y pimienta
                
                Preparación:
                1. Cocer la pasta en agua con sal.
                2. Sofreír el ajo en aceite.
                3. Mezclar y servir.
                """;
    }
}