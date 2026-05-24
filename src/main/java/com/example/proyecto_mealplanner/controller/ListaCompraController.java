package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.ListaCompraRequestDTO;
import com.example.proyecto_mealplanner.dto.response.ListaCompraResponseDTO;
import com.example.proyecto_mealplanner.service.ListaCompraService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lista-compra")
@RequiredArgsConstructor

public class ListaCompraController {

    private final ListaCompraService listaCompraService;

    @PostMapping
    public ResponseEntity<ListaCompraResponseDTO> crearItem(
            @RequestBody ListaCompraRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(listaCompraService.crearItem(dto));
    }
}