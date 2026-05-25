package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.ProcesarTextoRequestDTO;
import com.example.proyecto_mealplanner.dto.response.ProcesamientoStatusResponseDTO;
import com.example.proyecto_mealplanner.entity.Usuario;
import com.example.proyecto_mealplanner.event.TextoProcesadoEvent;
import com.example.proyecto_mealplanner.repository.UsuarioRepository;
import com.example.proyecto_mealplanner.service.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/process")
@RequiredArgsConstructor
public class ProcesamientoController {
    private final ApplicationEventPublisher eventPublisher;
    private final UsuarioRepository usuarioRepository;
    private final RecetaService recetaService;
    // Simulación de almacenamiento de tareas (en producción usar Redis o BD)
    private final ConcurrentHashMap<String, ProcesamientoStatusResponseDTO> tareas = new ConcurrentHashMap<>();

    @PostMapping("/text")
    public ResponseEntity<ProcesamientoStatusResponseDTO> procesarTexto(@Valid @RequestBody ProcesarTextoRequestDTO request) {
        String taskId = UUID.randomUUID().toString();
        ProcesamientoStatusResponseDTO status = ProcesamientoStatusResponseDTO.builder()
                .taskId(taskId)
                .estado("PENDING")
                .build();
        tareas.put(taskId, status);
        Usuario usuario = obtenerUsuarioActual();
        // Publicar evento asíncrono (debes crear TextoProcesadoEvent)
        eventPublisher.publishEvent(new TextoProcesadoEvent(this, request.getTexto(), usuario.getIdUsuario(), taskId));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }

    @GetMapping("/status/{taskId}")
    public ResponseEntity<ProcesamientoStatusResponseDTO> getStatus(@PathVariable String taskId) {
        ProcesamientoStatusResponseDTO status = tareas.getOrDefault(taskId,
                ProcesamientoStatusResponseDTO.builder().taskId(taskId).estado("NOT_FOUND").build());
        return ResponseEntity.ok(status);
    }

    private Usuario obtenerUsuarioActual() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email).orElseThrow();
    }
}
