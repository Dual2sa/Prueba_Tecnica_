/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pruebatecnica.aseo.repostories;

import com.pruebatecnica.aseo.models.DetalleFactura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author david
 */
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long>{
    //Consultas SQL Para traer todos los registros con id_factura determinada
     @Query("SELECT d FROM DetalleFactura d WHERE d.id_factura = :id_factura")
    List<DetalleFactura> findByIdFactura(@Param("id_factura") Long id_factura);
    
    //Consultas SQL Para traer todos los registros con id_producto determinado
    @Query("SELECT d FROM DetalleFactura d WHERE d.id_producto = :id_producto")
    List<DetalleFactura> findByIdProducto(@Param("id_producto") Long id_producto);
}
