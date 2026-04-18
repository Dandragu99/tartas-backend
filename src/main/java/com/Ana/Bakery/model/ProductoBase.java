package com.Ana.Bakery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos_base")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoBase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precioBase;
    private String imgPaso1;
    private String imgPaso2;
    private String imgPaso3;

    // A lo mejor lo cambio a Lazy
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "producto_ingredientes",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientesCompatibles = new ArrayList<>();
}