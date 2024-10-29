/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnica.aseo.services;

import com.pruebatecnica.aseo.models.Factura;
import com.pruebatecnica.aseo.repostories.ClienteRepository;
import com.pruebatecnica.aseo.repostories.DetalleFacturaRepository;
import com.pruebatecnica.aseo.repostories.FacturaRepository;
import com.pruebatecnica.aseo.repostories.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author david
 */
@Service
public class FacturaServices {
    @Autowired
    private FacturaRepository facturaRepository;

   
    
    public  List<Factura> getAllFacturas(){
        return facturaRepository.findAll();
    }
    public Optional <Factura> getFacturaById(Long num_factura){
        return facturaRepository.findById(num_factura);
    }
     public List <Factura> getFacturaByClienteId(Long id_cliente){
        return facturaRepository.findByIdCliente(id_cliente);
    }
    public Factura createFactura(Factura factura){
        return facturaRepository.save(factura);
    }
   public void deleteFactura(Long num_factura) {
        facturaRepository.deleteById(num_factura);
    }
    public Factura updateFactura(Long num_factura, Factura updateFactura) {
        updateFactura.setNum_factura(num_factura);
        return facturaRepository.save(updateFactura);
    }
     public boolean existsById(Long num_factura) {
        return facturaRepository.existsById(num_factura);
    }
}
