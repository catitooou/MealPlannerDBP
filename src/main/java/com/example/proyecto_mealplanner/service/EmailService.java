package com.example.proyecto_mealplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Async
    public void enviarCorreoRecetaGenerada(String destinatario, String tituloReceta) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Tu receta ha sido generada");
        message.setText("Hola,\n\nSe ha generado una nueva receta: " + tituloReceta + "\n\n¡Revisa tu perfil de MealPlanner!");
        mailSender.send(message);
    }

    @Async
    public void enviarCorreoBienvenida(String destinatario, String nombre) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("¡Bienvenido a MealPlanner!");
        message.setText("Hola " + nombre + ",\n\nGracias por registrarte en MealPlanner. ¡Disfruta convirtiendo videos en recetas estructuradas!");
        mailSender.send(message);
    }
}