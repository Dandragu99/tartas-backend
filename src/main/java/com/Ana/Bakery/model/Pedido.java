package com.Ana.Bakery.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;



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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotasAlergias() {
        return notasAlergias;
    }

    public void setNotasAlergias(String notasAlergias) {
        this.notasAlergias = notasAlergias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public ProductoBase getProductoBase() {
        return productoBase;
    }

    public void setProductoBase(ProductoBase productoBase) {
        this.productoBase = productoBase;
    }

    public List<Ingrediente> getIngredientesSeleccionados() {
        return ingredientesSeleccionados;
    }

    public void setIngredientesSeleccionados(List<Ingrediente> ingredientesSeleccionados) {
        this.ingredientesSeleccionados = ingredientesSeleccionados;
    }
}
