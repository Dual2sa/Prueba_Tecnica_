
package com.pruebatecnica.aseo.repostories;
import com.pruebatecnica.aseo.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

     
/**
 *
 * @author david
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
