package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.response.FavoritoResponseDTO;
import com.example.proyecto_mealplanner.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
public class FavoritoController {
    private final FavoritoService favoritoService;

    @PostMapping("/{recetaId}")
    public ResponseEntity<FavoritoResponseDTO> addFavorito(@PathVariable Long recetaId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.agregarFavorito(recetaId));
    }

    @DeleteMapping("/{recetaId}")
    public ResponseEntity<Void> removeFavorito(@PathVariable Long recetaId) {
        favoritoService.eliminarFavorito(recetaId);
        return ResponseEntity.noContent().build();
    }
}
