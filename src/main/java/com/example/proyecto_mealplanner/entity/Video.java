package com.example.proyecto_mealplanner.entity;

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
    private String urlS3;        // URL del video almacenado (S3 o local)

    @Enumerated(EnumType.STRING)
    private EstadoVideo estado = EstadoVideo.PENDIENTE;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "transcripcion_id")
    private Transcripcion transcripcion;

    @OneToOne
    @JoinColumn(name = "receta_generada_id")
    private Receta recetaGenerada; // receta que se creó a partir del video
}
