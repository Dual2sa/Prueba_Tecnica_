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

public class Factura {
     private int num_factura;
     private int id_cliente;
    private String fecha;

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Factura(int num_factura, int id_cliente, String fecha) {
        this.num_factura = num_factura;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
    }

    public Factura() {
        this.num_factura = num_factura;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
    }
    
}
