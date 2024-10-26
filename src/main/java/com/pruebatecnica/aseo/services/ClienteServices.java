package com.pruebatecnica.aseo.services;

import com.pruebatecnica.aseo.models.Cliente;
import com.pruebatecnica.aseo.repostories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id_cliente) {
        return clienteRepository.findById(id_cliente);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id_cliente) {
        clienteRepository.deleteById(id_cliente);
    }

    public Cliente updateCliente(Long id_cliente, Cliente updateCliente) {
        updateCliente.setId_cliente(id_cliente);
        return clienteRepository.save(updateCliente);
    }

    public boolean existsById(Long id_cliente) {
        return clienteRepository.existsById(id_cliente);
    }
}
