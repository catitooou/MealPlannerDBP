package com.example.proyecto_mealplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "recetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String pasos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "receta")
    private List<RecetaIngrediente> recetaIngredientes;

    @OneToMany(mappedBy = "receta")
    private List<Favorito> favoritos;
}