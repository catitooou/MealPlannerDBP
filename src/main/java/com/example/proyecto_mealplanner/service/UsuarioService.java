package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.response.UsuarioResponseDTO;
import com.example.proyecto_mealplanner.exception.ResourceNotFoundException;
import com.example.proyecto_mealplanner.mapper.Usuario;
import com.example.proyecto_mealplanner.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> obtenerUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> UsuarioResponseDTO.builder()
                        .idUsuario(usuario.getIdUsuario())
                        .nombre(usuario.getNombre())
                        .email(usuario.getEmail())
                        .build())
                .toList();
    }

    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));

        return UsuarioResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .build();
    }
}