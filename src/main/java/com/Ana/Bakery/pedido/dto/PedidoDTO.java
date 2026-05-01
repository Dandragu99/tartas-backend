package com.Ana.Bakery.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long idPedido;
    private Long usuarioId;
    private String nombreUsuario;
    private String productoBaseNombre;
    private List<String> ingredientes;
    private String estado;
    private Double precioTotal;
    private LocalDateTime fechaEntrega;
}
