package com.example.proyecto_mealplanner.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "listas_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ListaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLista;

    @Column(nullable = false)
    private String nombreIngrediente;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}