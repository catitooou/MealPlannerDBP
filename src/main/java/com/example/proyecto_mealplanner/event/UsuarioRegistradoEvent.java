package com.example.proyecto_mealplanner.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UsuarioRegistradoEvent extends ApplicationEvent {
    private final String email;
    private final String nombre;

    public UsuarioRegistradoEvent(Object source, String email, String nombre) {
        super(source);
        this.email = email;
        this.nombre = nombre;
    }
}
