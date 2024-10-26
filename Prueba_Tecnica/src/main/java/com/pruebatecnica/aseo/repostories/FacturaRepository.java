/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pruebatecnica.aseo.repostories;

import com.pruebatecnica.aseo.models.Factura;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author david
 */
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    //Consultas SQL Para traer todas las facturas de determiando cliente
 @Query("SELECT f FROM Factura f WHERE f.id_cliente = :id_cliente")
    List<Factura> findByIdCliente(@Param("id_cliente") Long id_cliente);
}
