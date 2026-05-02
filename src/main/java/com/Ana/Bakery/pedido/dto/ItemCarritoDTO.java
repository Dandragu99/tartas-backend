package com.Ana.Bakery.pedido.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemCarritoDTO {
    private Long productoBaseId;
    private List<Long> ingredientesIds;
    private Integer cantidad;
    private Double precioUnitario;
    private String alergias;
    private String mensaje;
}