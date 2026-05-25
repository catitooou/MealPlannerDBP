package com.example.proyecto_mealplanner.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transcripciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transcripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTranscripcion;

    @Column(columnDefinition = "TEXT")
    private String contenido;
}
