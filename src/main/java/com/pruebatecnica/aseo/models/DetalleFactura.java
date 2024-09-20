/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnica.aseo.models;
import jakarta.persistence.*;
/**
 *
 * @author david
 */
@Entity
@Table (name="detalle")
public class DetalleFactura {
     //Atributos Clase DetalleFactura
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_detalle;
    private Long id_factura;
    private Long id_producto;
    private int cantidad;
    private float precio;

    public Long getNum_detalle() {
        return num_detalle;
    }

    public void setNum_detalle(Long num_detalle) {
        this.num_detalle = num_detalle;
    }

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    
   
}
