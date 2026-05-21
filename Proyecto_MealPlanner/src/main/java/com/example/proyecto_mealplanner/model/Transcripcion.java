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
public class Transcripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTranscripcion;

    @Column(length = 10000)
    private String texto;

    private String idioma;

    @OneToOne
    @JoinColumn(name = "video_id")
    private Video video;

}