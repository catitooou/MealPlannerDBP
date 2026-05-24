package com.example.proyecto_mealplanner.controller;

import com.example.proyecto_mealplanner.dto.request.LoginRequestDTO;
import com.example.proyecto_mealplanner.dto.request.RegisterRequestDTO;

import com.example.proyecto_mealplanner.dto.response.AuthResponseDTO;

import com.example.proyecto_mealplanner.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/v1/auth")

@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(

            @Valid
            @RequestBody
            RegisterRequestDTO request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(

            @Valid
            @RequestBody
            LoginRequestDTO request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}