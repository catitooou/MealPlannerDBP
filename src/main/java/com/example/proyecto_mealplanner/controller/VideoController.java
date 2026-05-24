package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.VideoRequestDTO;
import com.example.proyecto_mealplanner.dto.response.VideoResponseDTO;
import com.example.proyecto_mealplanner.service.VideoService;

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
    public ResponseEntity<VideoResponseDTO> subirVideo(
            @RequestBody VideoRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(videoService.subirVideo(dto));
    }
}