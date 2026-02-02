package com.Ana.Bakery.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaIngrediente tipo;

    private Double precioAdicional;

    private Boolean disponible;

    public Ingrediente(Long id, String nombre, CategoriaIngrediente tipo, Double precioAdicional, Boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioAdicional = precioAdicional;
        this.disponible = disponible;
    }
}
