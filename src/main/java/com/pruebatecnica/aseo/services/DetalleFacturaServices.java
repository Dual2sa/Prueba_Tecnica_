/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatecnica.aseo.services;

import com.pruebatecnica.aseo.models.DetalleFactura;
import com.pruebatecnica.aseo.repostories.DetalleFacturaRepository;
import com.pruebatecnica.aseo.repostories.FacturaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.util.DetailedError;

/**
 *
 * @author david
 */
@Service
public class DetalleFacturaServices {
    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;
    
    
     
    public List<DetalleFactura> getAllDetalles(){
        return detalleFacturaRepository.findAll();
    }
    public Optional<DetalleFactura> getDetalleById(Long num_detalle){
        return detalleFacturaRepository.findById(num_detalle);
    }
    public List<DetalleFactura> getDetalleByIdFactura(Long num_factura){
        return detalleFacturaRepository.findByIdFactura(num_factura);
    }
     public List<DetalleFactura> getDetalleByIdProducto(Long id_producto){
        return detalleFacturaRepository.findByIdProducto(id_producto);
    }
     
       public boolean existsById(Long num_detalle) {
        return detalleFacturaRepository.existsById(num_detalle);
    }
       public void deleteDetelle(Long num_detalle) {
        detalleFacturaRepository.deleteById(num_detalle);
    }
        public DetalleFactura updateDetalleFactura(Long num_detalle, DetalleFactura updateDetalleFactura) {
        updateDetalleFactura.setNum_detalle(num_detalle);
        return detalleFacturaRepository.save(updateDetalleFactura);
    }
 public DetalleFactura createDetalle(DetalleFactura detalle) {
        return detalleFacturaRepository.save(detalle);
    }
}
