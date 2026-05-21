package com.example.proyecto_mealplanner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVideo;

    private String nombreArchivo;
    private String urlVideo;
    private String estadoProcesamiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}