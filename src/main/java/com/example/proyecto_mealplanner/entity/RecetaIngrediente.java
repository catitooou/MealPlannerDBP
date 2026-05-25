package com.example.proyecto_mealplanner.entity;

import com.example.proyecto_mealplanner.enums.UnidadMedida;
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

    @Enumerated(EnumType.STRING)
    private UnidadMedida unidad;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;
}
