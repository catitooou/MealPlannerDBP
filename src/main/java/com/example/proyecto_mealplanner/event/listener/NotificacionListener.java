package com.example.proyecto_mealplanner.event.listener;

import com.example.proyecto_mealplanner.event.UsuarioRegistradoEvent;
import com.example.proyecto_mealplanner.event.VideoSubidoEvent;
import com.example.proyecto_mealplanner.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificacionListener {

    private final EmailService emailService;

    @Async
    @EventListener
    public void handleUsuarioRegistrado(UsuarioRegistradoEvent event) {
        log.info("Enviando email de bienvenida a: {}", event.getEmail());
        emailService.enviarCorreoBienvenida(event.getEmail(), event.getNombre());
    }

    @Async
    @EventListener
    public void handleVideoSubido(VideoSubidoEvent event) {
        // Podríamos enviar un correo al usuario indicando que su video está en procesamiento
        // Pero eso requiere obtener el email del usuario. Para simplificar, lo dejamos opcional.
        // Si se desea, se puede inyectar UsuarioRepository y obtener el email.
        log.info("Notificación: video ID {} subido por usuario {}", event.getVideoId(), event.getUsuarioId());
    }
}