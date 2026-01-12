package com.MinhaAPIclientes.APIClentes.Repository;

import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
boolean existsByEmail(String email);
boolean existsByTelefone(String telefone);
    Page<Cliente> findByNomeContaining(String nome, Pageable pageable);
    boolean existsByNomeContaining(String nome);
    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);


}