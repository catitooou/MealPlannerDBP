package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.response.FavoritoResponseDTO;
import com.example.proyecto_mealplanner.model.Favorito;
import com.example.proyecto_mealplanner.service.FavoritoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favoritos")
@RequiredArgsConstructor

public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping
    public ResponseEntity<FavoritoResponseDTO> guardarFavorito(
            @RequestBody Favorito favorito
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(favoritoService.guardarFavorito(favorito));
    }
}