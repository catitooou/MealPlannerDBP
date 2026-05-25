package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.RecetaRequestDTO;
import com.example.proyecto_mealplanner.dto.response.RecetaResponseDTO;
import com.example.proyecto_mealplanner.service.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @GetMapping
    public ResponseEntity<Page<RecetaResponseDTO>> listarRecetas(Pageable pageable) {
        return ResponseEntity.ok(recetaService.listarRecetasPublicas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> obtenerReceta(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerRecetaPorId(id));
    }

    @PostMapping
    public ResponseEntity<RecetaResponseDTO> crearReceta(@Valid @RequestBody RecetaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaService.crearReceta(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> actualizarReceta(@PathVariable Long id, @Valid @RequestBody RecetaRequestDTO request) {
        return ResponseEntity.ok(recetaService.actualizarReceta(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @recetaService.esPropietario(#id, authentication.principal.id)")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }
}
