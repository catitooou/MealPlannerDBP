package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.response.UsuarioResponseDTO;
import com.example.proyecto_mealplanner.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() {

        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }
}