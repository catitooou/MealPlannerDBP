package com.example.proyecto_mealplanner.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class VideoSubidoEvent extends ApplicationEvent {
    private final Long videoId;
    private final Long usuarioId;

    public VideoSubidoEvent(Object source, Long videoId, Long usuarioId) {
        super(source);
        this.videoId = videoId;
        this.usuarioId = usuarioId;
    }
}