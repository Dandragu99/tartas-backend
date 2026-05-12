package com.Ana.Bakery.producto.productoBaseDTO;

import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoBaseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precioBase;
    private String imgPaso1;
    private String imgPaso2;
    private String imgPaso3;
    private String imgPaso4;
    private String imgPaso5;
    private List<Ingrediente> ingredientesCompatibles;
}