package com.example.proyecto_mealplanner.auth;

import com.example.proyecto_mealplanner.dto.request.LoginRequestDTO;
import com.example.proyecto_mealplanner.dto.request.RegisterRequestDTO;
import com.example.proyecto_mealplanner.dto.response.AuthResponseDTO;
import com.example.proyecto_mealplanner.entity.Usuario;
import com.example.proyecto_mealplanner.enums.Role;
import com.example.proyecto_mealplanner.event.UsuarioRegistradoEvent;
import com.example.proyecto_mealplanner.exception.DuplicateResourceException;
import com.example.proyecto_mealplanner.repository.UsuarioRepository;
import com.example.proyecto_mealplanner.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final ApplicationEventPublisher eventPublisher;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }
        Usuario user = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .active(true)
                .build();
        Usuario saved = usuarioRepository.save(user);

        // Publicar evento asíncrono de bienvenida
        eventPublisher.publishEvent(new UsuarioRegistradoEvent(this, saved.getEmail(), saved.getNombre()));

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthResponseDTO.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .userId(saved.getIdUsuario())
                .email(saved.getEmail())
                .role(saved.getRole().name())
                .build();
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Usuario user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthResponseDTO.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .userId(user.getIdUsuario())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
