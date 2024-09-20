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
     //GET traer todos los Productos
      @GetMapping
    public List<Producto> getAllProductos(){
     return productoRepository.findAll();
     
    }
     //GET traer producto por id_producto tomado desde URL
    @GetMapping("/{id_producto}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id_producto){
        Optional<Producto> producto= productoRepository.findById(id_producto);
        return producto.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
       //POST  crear nuevo  producto 
    @PostMapping("/crear")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto saveProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProducto);

    }
    //DELETE borrar producto por id_producto
    @DeleteMapping("/{id_producto}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id_producto){
        if(!productoRepository.existsById(id_producto)){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con id " + id_producto + " no existe");
        }
        productoRepository.deleteById(id_producto);
        return ResponseEntity.noContent().build();
    }
    //PUT actualizar producto por id_producto
    @PutMapping("/{id_producto}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id_producto, @RequestBody Producto updateCliente ){
        if(!productoRepository.existsById(id_producto)){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con id " + id_producto + " no existe");
        }
        updateCliente.setId_producto(id_producto);
       Producto saveCliente= productoRepository.save(updateCliente);
        return ResponseEntity.ok(saveCliente);
    }
}
