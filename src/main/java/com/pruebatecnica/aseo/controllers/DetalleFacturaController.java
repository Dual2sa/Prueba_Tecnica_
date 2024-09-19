/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.DetalleFactura;
import com.pruebatecnica.aseo.models.Factura;
import com.pruebatecnica.aseo.models.Producto;
import com.pruebatecnica.aseo.repostories.DetalleFacturaRepository;
import com.pruebatecnica.aseo.repostories.FacturaRepository;
import com.pruebatecnica.aseo.repostories.ProductoRepository;
import com.pruebatecnica.aseo.repostories.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author david
 */
@RestController
@RequestMapping("api/detalles")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @GetMapping
    public List<DetalleFactura> getAllDetalles() {
        return detalleFacturaRepository.findAll();
    }

    @GetMapping("/detalle/{num_detalle}")
    public ResponseEntity<DetalleFactura> getDetallebyId(@PathVariable Long num_detalle) {
        Optional<DetalleFactura> detalleFactura = detalleFacturaRepository.findById(num_detalle);
        return detalleFactura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/factura/{id_factura}")
    public ResponseEntity<List<DetalleFactura>> getDetallesByFacturaId(@PathVariable Long id_factura) {
        List<DetalleFactura> detalles = detalleFacturaRepository.findByIdFactura(id_factura);
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(detalles); 
    }
    @GetMapping("/producto/{id_producto}")
    public ResponseEntity<List<DetalleFactura>> getDetallesByProductoIId(@PathVariable Long id_producto) {
        List<DetalleFactura> detalles = detalleFacturaRepository.findByIdProducto(id_producto);
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(detalles);
    }
   
}
