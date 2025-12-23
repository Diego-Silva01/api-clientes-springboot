package com.MinhaAPIclientes.APIClentes.Repository;

import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
