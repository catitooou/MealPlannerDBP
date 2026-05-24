package com.example.proyecto_mealplanner.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favoritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavorito;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
}