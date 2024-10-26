package com.pruebatecnica.aseo.services;

import com.pruebatecnica.aseo.models.Producto;
import com.pruebatecnica.aseo.repostories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServices {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id_producto) {
        return productoRepository.findById(id_producto);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id_producto) {
        productoRepository.deleteById(id_producto);
    }

    public Producto updateProducto(Long id_producto, Producto updateProducto) {
        updateProducto.setId_producto(id_producto);
        return productoRepository.save(updateProducto);
    }

    public boolean existsById(Long id_producto) {
        return productoRepository.existsById(id_producto);
    }
}
