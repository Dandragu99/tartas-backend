package com.Ana.Bakery.pedido.pedidoModel;


import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import com.Ana.Bakery.producto.productoModel.ProductoBase;
import com.Ana.Bakery.usuario.usuarioModel.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "usuario_id", nullable = false)
    private Usuario usuario;

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
