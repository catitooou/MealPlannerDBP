package com.example.proyecto_mealplanner.model;

import com.example.proyecto_mealplanner.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "usuario")
    private List<Receta> recetas;

    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "usuario")
    private List<ListaCompra> listasCompra;
}