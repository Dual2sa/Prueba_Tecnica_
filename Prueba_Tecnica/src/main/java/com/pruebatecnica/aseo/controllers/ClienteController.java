

package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.Cliente;
import com.pruebatecnica.aseo.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices clienteService;

    @GetMapping()
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id_cliente) {
        Optional<Cliente> cliente = clienteService.getClienteById(id_cliente);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente saveCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCliente);
    }

    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id_cliente) {
        if (!clienteService.existsById(id_cliente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente con id " + id_cliente + " no encontrado.");
        }
        clienteService.deleteCliente(id_cliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id_cliente, @RequestBody Cliente updateCliente) {
        if (!clienteService.existsById(id_cliente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente con id " + id_cliente + " no encontrado.");
        }
        Cliente saveCliente = clienteService.updateCliente(id_cliente, updateCliente);
        return ResponseEntity.ok(saveCliente);
    }
}
