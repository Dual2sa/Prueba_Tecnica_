package com.pruebatecnica.aseo.controllers;

import com.pruebatecnica.aseo.models.Cliente;
import com.pruebatecnica.aseo.repostories.ClienteRepository;
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
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
 //Get para traer todos los datos
    @GetMapping()
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();

    }
 //Get para traer todos los datos de un determinado cliente
    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id_cliente) {
        Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
//Post para la creacion de un nuevo cliente
    @PostMapping("/crear")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente saveCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCliente);

    }
    //DELETE para borrar un cliente, Toma el id_cliente de la URL
    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id_cliente){
        //Verificar que el cliente a eliminar exista
        if(!clienteRepository.existsById(id_cliente)){
              //Si no existe se envia un Response con error NotFound
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente  con id " + id_cliente + " no encontrado.");
        }
        //Si existe se borra utilizando la funcion deleteById
        clienteRepository.deleteById(id_cliente);
        return ResponseEntity.noContent().build();
    }
     //PUT actualizacion de los datos, Toma el id_cliente desde la URL
    @PutMapping("/{id_cliente}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id_cliente, @RequestBody Cliente updateCliente ){
         //Verificar que el cliente a acutalizar sus datos exista
        if(!clienteRepository.existsById(id_cliente)){
            //Si no existe se envia un Response con error NotFound
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente  con id " + id_cliente + " no encontrado.");
        }
         //Si existe se actualiza los registros
        updateCliente.setId_cliente(id_cliente);
       Cliente saveCliente= clienteRepository.save(updateCliente);
        return ResponseEntity.ok(saveCliente);
    }
    
}
