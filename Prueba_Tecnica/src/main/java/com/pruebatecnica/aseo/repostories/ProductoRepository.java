
package com.pruebatecnica.aseo.repostories;

import com.pruebatecnica.aseo.models.Producto;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author david
 */

public interface ProductoRepository extends JpaRepository<Producto, Long >{
    
   
}
