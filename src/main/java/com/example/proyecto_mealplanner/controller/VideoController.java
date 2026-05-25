package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.VideoRequestDTO;
import com.example.proyecto_mealplanner.dto.response.VideoResponseDTO;
import com.example.proyecto_mealplanner.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<VideoResponseDTO> subirVideo(@Valid @RequestBody VideoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.subirVideo(request));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<VideoResponseDTO> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.obtenerEstado(id));
    }
}