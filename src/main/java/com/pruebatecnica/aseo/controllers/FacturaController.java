
package com.pruebatecnica.aseo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author david
 */
@Controller
public class FacturaController {
    @GetMapping("/")
    public String indice(){
        return "index";
    }
     @GetMapping("/vistaClientes")
    public String clientes() {
        return "vistaClientes";  // Este retorna el archivo clientes.html
    }
    @GetMapping("/vistaFacturas")
    public String facturas() {
        return "vistaFacturas";  // Este retorna el archivo clientes.html
    }
    @GetMapping("/vistaProductos")
    public String productos() {
        return "vistaProductos";  // Este retorna el archivo clientes.html
    }
    
}
