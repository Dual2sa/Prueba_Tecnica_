package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.Cliente;
import com.pruebatecnica.aseo.models.DetalleFactura;
import com.pruebatecnica.aseo.models.Factura;
import com.pruebatecnica.aseo.models.Producto;
import com.pruebatecnica.aseo.repostories.ClienteRepository;
import com.pruebatecnica.aseo.repostories.DetalleFacturaRepository;
import com.pruebatecnica.aseo.repostories.FacturaRepository;
import com.pruebatecnica.aseo.repostories.ProductoRepository;
import com.pruebatecnica.aseo.services.FacturaServices;
import com.pruebatecnica.aseo.services.ClienteServices;
import com.pruebatecnica.aseo.services.DetalleFacturaServices;
import com.pruebatecnica.aseo.services.ProductoServices;
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
    private FacturaServices facturaServices;
 @Autowired
    private ClienteServices clienteServices;
     @Autowired
    private ProductoServices productoServices;
  @Autowired
    private DetalleFacturaServices detalleFacturaServices;

    @GetMapping
    // Obtener todas las facturas
    public List<Factura> getAllFacturas() {
        return facturaServices.getAllFacturas();
    }
    //GET Obtener factura por num_factura tomado desde URL

    @GetMapping("/factura/{num_factura}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long num_factura) {
        Optional<Factura> factura = facturaServices.getFacturaById(num_factura);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //GET Obtener factura por id_cliente  tomado desde URL

    @GetMapping("/cliente/{id_cliente}")
    public ResponseEntity<List<Factura>> getFacturasByClienteId(@PathVariable Long id_cliente) {
        List<Factura> facturas = facturaServices.getFacturaByClienteId(id_cliente);
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
        Optional<Cliente> cliente = clienteServices.getClienteById(id_cliente);
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente con id " + id_cliente + " no encontrado.");
        }

        // Verificar si el producto existe
        Optional<Producto> producto = productoServices.getProductoById(id_producto);
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
        Factura nuevaFactura = facturaServices.createFactura(factura);

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
        productoServices.createProducto(productoEncontrado);
        detalleFacturaServices.createDetalle(detalle);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Factura y Detalle creados correctamente con un total de " + total);
        response.put("factura", nuevaFactura);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    // DELETE borrar factura por num_factura tomado desde la URL

    @DeleteMapping("/{num_factura}")
    public ResponseEntity<?> deleteFactura(@PathVariable Long num_factura) {
        if (!facturaServices.existsById(num_factura)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Factura  con id " + num_factura + " no existe.");
        }
        facturaServices.deleteFactura(num_factura);
        return ResponseEntity.noContent().build();
    }
    // PUT actualizarr factura por num_factura tomado desde la URL

    @PutMapping("/{num_factura}")
    public ResponseEntity<?> updateFactura(@PathVariable Long num_factura, @RequestBody Factura updateFactura) {
        if (!facturaServices.existsById(num_factura)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Factura  con id " + num_factura + " no existe.");
        }
        Factura saveFactura = facturaServices.updateFactura(num_factura, updateFactura);
        return ResponseEntity.ok(saveFactura);
    }

}
