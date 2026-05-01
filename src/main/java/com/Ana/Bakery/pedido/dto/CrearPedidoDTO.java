package com.Ana.Bakery.pedido.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CrearPedidoDTO {

    private Long usuarioId;
    private Long productoBaseId;
    private List<Long> ingredientesIds;
    private String notasAlergias;
    private LocalDateTime fechaEntrega;
}
