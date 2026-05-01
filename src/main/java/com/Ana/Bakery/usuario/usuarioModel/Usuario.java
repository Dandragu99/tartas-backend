package com.Ana.Bakery.usuario.usuarioModel;

import com.Ana.Bakery.pedido.pedidoModel.Pedido;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;


    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
    private String nombreCompleto;
    private String telefono;

    @Column(nullable = false)
    private String rol;


}