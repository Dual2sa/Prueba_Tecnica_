/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.Producto;
import com.pruebatecnica.aseo.repostories.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("api/productos")
public class ProductoController {
     @Autowired
    private ProductoRepository productoRepository;
     
      @GetMapping
    public List<Producto> getAllProductos(){
     return productoRepository.findAll();
     
    }
    @GetMapping("/{id_producto}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id_producto){
        Optional<Producto> producto= productoRepository.findById(id_producto);
        return producto.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
     
    @PostMapping()
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto saveProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProducto);

    }
    
    @DeleteMapping("/{id_producto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id_producto){
        if(!productoRepository.existsById(id_producto)){
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id_producto);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id_producto}")
    public ResponseEntity<Producto> updateCliente(@PathVariable Long id_producto, @RequestBody Producto updateCliente ){
        if(!productoRepository.existsById(id_producto)){
            return ResponseEntity.notFound().build();
        }
        updateCliente.setId_producto(id_producto);
       Producto saveCliente= productoRepository.save(updateCliente);
        return ResponseEntity.ok(saveCliente);
    }
}
