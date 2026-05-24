package com.example.proyecto_mealplanner.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "receta_ingrediente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RecetaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cantidad;

    private String unidad;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
}