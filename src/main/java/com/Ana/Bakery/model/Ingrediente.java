package com.Ana.Bakery.model;

import jakarta.persistence.*;
import lombok.*;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaIngrediente getTipo() {
        return tipo;
    }

    public void setTipo(CategoriaIngrediente tipo) {
        this.tipo = tipo;
    }

    public Double getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(Double precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
