package com.Ana.Bakery.pedido.mapper;

import com.Ana.Bakery.pedido.dto.PedidoDTO;
import com.Ana.Bakery.pedido.pedidoModel.Pedido;

public class PedidoMapper {
    public PedidoDTO toDto(Pedido pedido){
        return new PedidoDTO(
                pedido.getId(),
                pedido.getUsuario().getNombreCompleto(),
                pedido.getProductoBase().getNombre(),
                pedido.getIngredientesSeleccionados()
                        .stream().map(ingrediente -> ingrediente.getNombre())
                        .toList(),
                pedido.getEstado(),
                pedido.getPrecioTotal(),
                pedido.getFechaEntrega()
        );
    }
}
