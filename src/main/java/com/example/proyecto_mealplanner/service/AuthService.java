package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.LoginRequestDTO;
import com.example.proyecto_mealplanner.dto.request.RegisterRequestDTO;
import com.example.proyecto_mealplanner.dto.response.AuthResponseDTO;
import com.example.proyecto_mealplanner.enums.Role;
import com.example.proyecto_mealplanner.model.Usuario;
import com.example.proyecto_mealplanner.repository.UsuarioRepository;
import com.example.proyecto_mealplanner.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("El email ya existe");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(Role.USER)
                .build();

        usuarioRepository.save(usuario);

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        usuario.getEmail(),
                        usuario.getPassword(),
                        java.util.List.of()
                );

        String token = jwtService.generateToken(userDetails);

        return AuthResponseDTO.builder()
                .token(token)
                .mensaje("Usuario registrado correctamente")
                .build();
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        usuario.getEmail(),
                        usuario.getPassword(),
                        java.util.List.of()
                );

        String token = jwtService.generateToken(userDetails);

        return AuthResponseDTO.builder()
                .token(token)
                .mensaje("Login exitoso")
                .build();
    }
}