/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnica.aseo.controllers;
import com.pruebatecnica.aseo.models.Producto;
import com.pruebatecnica.aseo.services.ProductoServices;

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
    private ProductoServices productoService;

    @GetMapping()
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id_producto}")
    public ResponseEntity<Producto> getClienteById(@PathVariable Long id_producto) {
        Optional<Producto> producto = productoService.getProductoById(id_producto);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto saveProducto = productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProducto);
    }

    @DeleteMapping("/{id_producto}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id_producto) {
        if (!productoService.existsById(id_producto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con id " + id_producto + " no encontrado.");
        }
        productoService.deleteProducto(id_producto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id_producto}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id_producto, @RequestBody Producto updateProducto) {
        if (!productoService.existsById(id_producto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con id " + id_producto + " no encontrado.");
        }
        Producto saveProducto = productoService.updateProducto(id_producto, updateProducto);
        return ResponseEntity.ok(saveProducto);
    }
}
