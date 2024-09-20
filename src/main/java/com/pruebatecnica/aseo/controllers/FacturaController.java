package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.Cliente;
import com.pruebatecnica.aseo.models.DetalleFactura;
import com.pruebatecnica.aseo.models.Factura;
import com.pruebatecnica.aseo.models.Producto;
import com.pruebatecnica.aseo.repostories.ClienteRepository;
import com.pruebatecnica.aseo.repostories.DetalleFacturaRepository;
import com.pruebatecnica.aseo.repostories.FacturaRepository;
import com.pruebatecnica.aseo.repostories.ProductoRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("api/facturas")

public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    // Obtener todas las facturas
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }
    //GET Obtener factura por num_factura tomado desde URL

    @GetMapping("/factura/{num_factura}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long num_factura) {
        Optional<Factura> factura = facturaRepository.findById(num_factura);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //GET Obtener factura por id_cliente  tomado desde URL

    @GetMapping("/cliente/{id_cliente}")
    public ResponseEntity<List<Factura>> getFacturasByClienteId(@PathVariable Long id_cliente) {
        List<Factura> facturas = facturaRepository.findByIdCliente(id_cliente);
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    // POST Crear una nueva factura
    @PostMapping("/crear/cliente/{id_cliente}/producto/{id_producto}/cantidad/{cantidad}")
    public ResponseEntity<?> createFacturaConDetalle(@PathVariable Long id_cliente, @PathVariable Long id_producto, @PathVariable int cantidad,
            @RequestBody Factura factura) {

        // Verificar si el cliente existe
        Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente con id " + id_cliente + " no encontrado.");
        }

        // Verificar si el producto existe
        Optional<Producto> producto = productoRepository.findById(id_producto);
        if (!producto.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con id " + id_producto + " no encontrado.");
        }
        Producto productoEncontrado = producto.get();
        // Verificar si la cantidad es válida (debe ser mayor que 0)
        if (cantidad <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La cantidad debe ser mayor a 0.");
        }
        if (cantidad > productoEncontrado.getStock()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cantidad solicitada (" + cantidad + ") excede el stock disponible ("
                            + productoEncontrado.getStock() + ").");
        }

        // Asignar el id_cliente a la factura
        factura.setId_cliente(id_cliente);
        Factura nuevaFactura = facturaRepository.save(factura);

        // Crear el detalle asociado a la factura
        DetalleFactura detalle = new DetalleFactura();
        detalle.setId_factura(nuevaFactura.getNum_factura());
        detalle.setId_producto(id_producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(producto.get().getPrecio());
        // Calcular el total (precio * cantidad)
        float total = producto.get().getPrecio() * cantidad;
        detalle.setPrecio(total);

        productoEncontrado.setStock(productoEncontrado.getStock() - cantidad);
        productoRepository.save(productoEncontrado);
        detalleFacturaRepository.save(detalle);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Factura y Detalle creados correctamente con un total de " + total);
        response.put("factura", nuevaFactura);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    // DELETE borrar factura por num_factura tomado desde la URL

    @DeleteMapping("/{num_factura}")
    public ResponseEntity<?> deleteFactura(@PathVariable Long num_factura) {
        if (!facturaRepository.existsById(num_factura)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Factura  con id " + num_factura + " no existe.");
        }
        facturaRepository.deleteById(num_factura);
        return ResponseEntity.noContent().build();
    }
    // PUT actualizarr factura por num_factura tomado desde la URL

    @PutMapping("/{num_factura}")
    public ResponseEntity<?> updateFactura(@PathVariable Long num_factura, @RequestBody Factura updateFactura) {
        if (!facturaRepository.existsById(num_factura)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Factura  con id " + num_factura + " no existe.");
        }
        updateFactura.setNum_factura(num_factura);
        Factura saveFactura = facturaRepository.save(updateFactura);
        return ResponseEntity.ok(saveFactura);
    }

}
