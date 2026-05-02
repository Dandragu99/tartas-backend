package com.Ana.Bakery.pedido.dto;

import lombok.Data;

import java.util.List;

@Data
public class CrearPedidoCarritoDTO {
    private Long usuarioId;
    private List<ItemCarritoDTO> items;
    private Double precioTotal;
}
