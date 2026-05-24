package com.example.proyecto_mealplanner.model;

import com.example.proyecto_mealplanner.enums.EstadoVideo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVideo;

    private String nombreArchivo;

    private String urlVideo;

    @Enumerated(EnumType.STRING)
    private EstadoVideo estado;

    @OneToOne
    @JoinColumn(name = "transcripcion_id")
    private Transcripcion transcripcion;
}