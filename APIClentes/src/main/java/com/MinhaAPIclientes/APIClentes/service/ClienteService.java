package com.MinhaAPIclientes.APIClentes.service;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import com.MinhaAPIclientes.APIClentes.exception.ClienteNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(ClienteDTO DTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(DTO.getNome());
        cliente.setEmail(DTO.getEmail());
        cliente.setTelefone(DTO.getTelefone());
        cliente.setEndereco(DTO.getEndereco());
        return repository.save(cliente);
    }

    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id));

    }

    public Cliente atualizarCliente(Long id, ClienteDTO DTO) {
        Cliente clientedoBanco = repository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id));
        clientedoBanco.setNome(DTO.getNome());
        clientedoBanco.setEmail(DTO.getEmail());
        return repository.save(clientedoBanco);

    }

        public void deletarUsuario(Long id) {
            repository.findById(id)
                    .orElseThrow(() -> new ClienteNaoEncontradoException(("Cliente não encontrado com id: " + id + ", não e possivel deletar!")));
           repository.deleteById(id);
        }
    }


