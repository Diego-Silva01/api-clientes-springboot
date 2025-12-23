package com.MinhaAPIclientes.APIClentes.service;

import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
            this.repository = repository;
    }

    public Cliente salvar( Cliente cliente) {
        return repository.save(cliente);

    }

    public List<Cliente> buscarTodos() {
        return repository.findAll();

    }


    public Cliente buscarPorID( Long id) {
        Cliente cliente = repository.findById(id).get();
        return cliente;


    }

    public Cliente atualizarCliente(Long id,Cliente clienteNovo ) {
        Cliente clientedoBanco = repository.findById(id).get();
        clientedoBanco.setNome(clienteNovo.getNome());
        clientedoBanco.setEmail(clienteNovo.getEmail());
        return repository.save(clientedoBanco);



    }

    public void deletarUsuario(Long id){
        repository.deleteById(id);
        System.out.println("Deletado");



    }
}


