package com.example.proyecto_mealplanner.event.listener;

import com.example.proyecto_mealplanner.entity.*;
import com.example.proyecto_mealplanner.enums.EstadoVideo;
import com.example.proyecto_mealplanner.event.TextoProcesadoEvent;
import com.example.proyecto_mealplanner.event.VideoSubidoEvent;
import com.example.proyecto_mealplanner.repository.*;
import com.example.proyecto_mealplanner.service.EmailService;
import com.example.proyecto_mealplanner.service.ProcesamientoTextoService;
import com.example.proyecto_mealplanner.service.WhisperService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class ProcesamientoListener {

    private final VideoRepository videoRepository;
    private final WhisperService whisperService;
    private final ProcesamientoTextoService procesamientoTextoService;
    private final RecetaRepository recetaRepository;
    private final TranscripcionRepository transcripcionRepository;
    private final EmailService emailService;
    private final UsuarioRepository usuarioRepository;

    // Opcional: seguimiento de tareas para el endpoint de estado
    private final ConcurrentHashMap<String, String> tareaEstado = new ConcurrentHashMap<>();

    @Async
    @EventListener
    @Transactional
    public void handleVideoSubido(VideoSubidoEvent event) {
        Video video = videoRepository.findById(event.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video no encontrado"));

        try {
            // Actualizar estado: procesando audio
            video.setEstado(EstadoVideo.PROCESANDO_AUDIO);
            videoRepository.save(video);

            // 1. Transcribir audio usando Whisper (simulado o real)
            String transcripcionTexto = whisperService.transcribirAudio(video.getUrlS3());

            // 2. Guardar transcripción
            Transcripcion transcripcion = Transcripcion.builder()
                    .contenido(transcripcionTexto)
                    .build();
            transcripcion = transcripcionRepository.save(transcripcion);
            video.setTranscripcion(transcripcion);
            video.setEstado(EstadoVideo.TRANSCRIBIENDO);
            videoRepository.save(video);

            // 3. Generar receta a partir del texto
            Usuario usuario = usuarioRepository.findById(event.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Receta recetaGenerada = procesamientoTextoService.generarRecetaDesdeTexto(transcripcionTexto, usuario);
            recetaRepository.save(recetaGenerada);

            // 4. Vincular receta generada al video
            video.setRecetaGenerada(recetaGenerada);
            video.setEstado(EstadoVideo.COMPLETADO);
            videoRepository.save(video);

            // 5. Enviar notificación por email
            emailService.enviarCorreoRecetaGenerada(usuario.getEmail(), recetaGenerada.getTitulo());

        } catch (Exception e) {
            video.setEstado(EstadoVideo.ERROR);
            videoRepository.save(video);
            // Loggear error (puedes usar Logger)
        }
    }

    @Async
    @EventListener
    @Transactional
    public void handleTextoProcesado(TextoProcesadoEvent event) {
        try {
            Usuario usuario = usuarioRepository.findById(event.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Receta receta = procesamientoTextoService.generarRecetaDesdeTexto(event.getTexto(), usuario);
            recetaRepository.save(receta);

            // Actualizar estado de la tarea si se usa
            tareaEstado.put(event.getTaskId(), "COMPLETED");

            // Notificar al usuario
            emailService.enviarCorreoRecetaGenerada(usuario.getEmail(), receta.getTitulo());

        } catch (Exception e) {
            tareaEstado.put(event.getTaskId(), "ERROR: " + e.getMessage());
        }
    }

    // Método para consultar estado de tarea (opcional, puedes exponerlo en un controlador)
    public String getTareaEstado(String taskId) {
        return tareaEstado.getOrDefault(taskId, "NOT_FOUND");
    }
}