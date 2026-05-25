package com.example.proyecto_mealplanner.entity;

import com.example.proyecto_mealplanner.enums.UnidadMedida;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lista_compra_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaCompraItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @ManyToOne
    @JoinColumn(name = "lista_id", nullable = false)
    private ListaCompra lista;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

    private Double cantidad;

    @Enumerated(EnumType.STRING)
    private UnidadMedida unidad;

    private boolean marcado = false; // checkbox para comprado
}