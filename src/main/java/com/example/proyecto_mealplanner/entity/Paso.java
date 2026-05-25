package com.example.proyecto_mealplanner.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pasos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaso;

    @Column(nullable = false)
    private Integer orden;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;
}
