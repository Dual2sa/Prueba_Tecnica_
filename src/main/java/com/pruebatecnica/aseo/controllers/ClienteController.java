package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.Cliente;
import com.pruebatecnica.aseo.repostories.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping()
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();

    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id_cliente) {
        Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente saveCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCliente);

    }
    
    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id_cliente){
        if(!clienteRepository.existsById(id_cliente)){
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id_cliente);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id_cliente}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id_cliente, @RequestBody Cliente updateCliente ){
        if(!clienteRepository.existsById(id_cliente)){
            return ResponseEntity.notFound().build();
        }
        updateCliente.setId_cliente(id_cliente);
       Cliente saveCliente= clienteRepository.save(updateCliente);
        return ResponseEntity.ok(saveCliente);
    }
    
}
