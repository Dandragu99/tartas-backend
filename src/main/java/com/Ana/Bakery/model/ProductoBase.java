package com.Ana.Bakery.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos_base")
public class ProductoBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precioBase;
    private String imgPaso1;
    private String imgPaso2;
    private String imgPaso3;

    public ProductoBase() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecioBase() { return precioBase; }
    public void setPrecioBase(Double precioBase) { this.precioBase = precioBase; }

    public String getImgPaso1() { return imgPaso1; }
    public void setImgPaso1(String imgPaso1) { this.imgPaso1 = imgPaso1; }

    public String getImgPaso2() { return imgPaso2; }
    public void setImgPaso2(String imgPaso2) { this.imgPaso2 = imgPaso2; }

    public String getImgPaso3() { return imgPaso3; }
    public void setImgPaso3(String imgPaso3) { this.imgPaso3 = imgPaso3; }
}