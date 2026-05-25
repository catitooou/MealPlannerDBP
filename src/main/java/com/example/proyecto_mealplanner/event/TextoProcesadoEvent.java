package com.example.proyecto_mealplanner.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TextoProcesadoEvent extends ApplicationEvent {
    private final String texto;
    private final Long usuarioId;
    private final String taskId;

    public TextoProcesadoEvent(Object source, String texto, Long usuarioId, String taskId) {
        super(source);
        this.texto = texto;
        this.usuarioId = usuarioId;
        this.taskId = taskId;
    }
}
