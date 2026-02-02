package com.Ana.Bakery.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String notasAlergias;

    private String estado;
    private Double precioTotal;
    private LocalDateTime fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "producto_base_id")
    private ProductoBase productoBase;

    @ManyToMany
    @JoinTable(
            name = "pedido_ingredientes",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientesSeleccionados;

}
