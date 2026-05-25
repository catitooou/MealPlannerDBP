package com.example.proyecto_mealplanner.entity;

import com.example.proyecto_mealplanner.enums.CategoriaIngrediente;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "ingredientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaIngrediente categoria;

    @OneToMany(mappedBy = "ingrediente")
    private List<RecetaIngrediente> recetaIngredientes;
}
