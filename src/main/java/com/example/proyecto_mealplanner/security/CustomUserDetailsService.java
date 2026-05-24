package com.example.proyecto_mealplanner.security;

import com.example.proyecto_mealplanner.model.Usuario;
import com.example.proyecto_mealplanner.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomUserDetailsService
        implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado"));

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(
                                usuario.getRole().name()
                        )
                )
        );
    }
}