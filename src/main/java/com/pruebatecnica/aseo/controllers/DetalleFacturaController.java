/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.DetalleFactura;
import com.pruebatecnica.aseo.repostories.ClienteRepository;

import com.pruebatecnica.aseo.repostories.DetalleFacturaRepository;
import com.pruebatecnica.aseo.repostories.FacturaRepository;
import com.pruebatecnica.aseo.repostories.ProductoRepository;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private ProductoRepository productoRepository;
            
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private FacturaRepository facturaRepository;
    //Get para traer todos los datos
    @GetMapping
    public List<DetalleFactura> getAllDetalles() {
        return detalleFacturaRepository.findAll();
    }

    //Get para traer todos los datos de un determinado detalle
    @GetMapping("/{num_detalle}")
    public ResponseEntity<DetalleFactura> getDetallebyId(@PathVariable Long num_detalle) {
        Optional<DetalleFactura> detalleFactura = detalleFacturaRepository.findById(num_detalle);
        return detalleFactura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
//Get para traer todos los datos de un determinado detalle usando id_factura

    @GetMapping("/factura/{id_factura}")
    public ResponseEntity<List<DetalleFactura>> getDetallesByFacturaId(@PathVariable Long id_factura) {
        List<DetalleFactura> detalles = detalleFacturaRepository.findByIdFactura(id_factura);
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalles);
    }

    //Get para traer todos los datos de un determinado detalle usando id_producto
    @GetMapping("/producto/{id_producto}")
    public ResponseEntity<List<DetalleFactura>> getDetallesByProductoIId(@PathVariable Long id_producto) {
        List<DetalleFactura> detalles = detalleFacturaRepository.findByIdProducto(id_producto);
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalles);
    }
    
     
    //DELETE borrar un registro tomando el num_detalle desde la URL
    @DeleteMapping("/{num_detalle}")
    public ResponseEntity<Void> deleteDetalleFactura(@PathVariable Long num_detalle) {
        if (!detalleFacturaRepository.existsById(num_detalle)) {
            return ResponseEntity.notFound().build();
        }
        detalleFacturaRepository.deleteById(num_detalle);
        return ResponseEntity.noContent().build();
    }
    //PUT Actualizar tomando el num_detalle desde la URL

    @PutMapping("/{num_detalle}")
    public ResponseEntity<DetalleFactura> updateDetalleFactura(@PathVariable Long num_detalle, @RequestBody DetalleFactura updateFactura) {
        if (!detalleFacturaRepository.existsById(num_detalle)) {
            return ResponseEntity.notFound().build();
        }
        updateFactura.setNum_detalle(num_detalle);
        DetalleFactura saveDetalleFactura = detalleFacturaRepository.save(updateFactura);
        return ResponseEntity.ok(saveDetalleFactura);
    }
}
