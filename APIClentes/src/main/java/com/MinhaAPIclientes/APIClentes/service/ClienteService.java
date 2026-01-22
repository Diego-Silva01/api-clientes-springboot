package com.MinhaAPIclientes.APIClentes.service;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import com.MinhaAPIclientes.APIClentes.exception.ClienteNaoEncontradoException;
import com.MinhaAPIclientes.APIClentes.exception.EmailJaCadastradoException;
import com.MinhaAPIclientes.APIClentes.exception.NomeNaoEncontrdo;
import com.MinhaAPIclientes.APIClentes.exception.NumeroDeTelefoneJaCadastrado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco()
        );
    }


    public ClienteResponseDTO salvar(ClienteDTO clienteDTO) {

        if (repository.existsByEmail(clienteDTO.getEmail())) {
            throw new EmailJaCadastradoException(
                    "E-mail já cadastrado: " + clienteDTO.getEmail()
            );
        }

        if (repository.existsByTelefone(clienteDTO.getTelefone())) {
            throw new NumeroDeTelefoneJaCadastrado(
                    "Número de telefone já cadastrado: " + clienteDTO.getTelefone()
            );
        }

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());
        Cliente clienteSalvo = repository.save(cliente);

        return toResponseDTO(clienteSalvo);

    }
    public Page<ClienteResponseDTO> buscarTodosPaginado(Pageable pageable, String nome) {
        Page<Cliente> page;
        if (nome == null || nome.isBlank()) {
            page = repository.findAll(pageable);

        } else {
            page = repository.findByNomeContainingIgnoreCase(nome, pageable);

        }
        return page.map(this::toResponseDTO);
    }

    public ClienteResponseDTO buscarPorID(Long id) {
        return repository.findById(id).map(this::toResponseDTO)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id));

    }
    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente clienteDoBanco = repository.findById(id)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException(
                                "Cliente não encontrado com id: " + id
                        )
                );
        if (clienteDTO.getNome() != null) {
            clienteDoBanco.setNome(clienteDTO.getNome());
        }

        if (clienteDTO.getEndereco() != null) {
            clienteDoBanco.setEndereco(clienteDTO.getEndereco());
        }

        if (clienteDTO.getEmail() != null && !clienteDoBanco.getEmail().equals(clienteDTO.getEmail())) {
            if (repository.existsByEmail(clienteDTO.getEmail())) {
                throw new EmailJaCadastradoException(
                        "E-mail já cadastrado: " + clienteDTO.getEmail()
                );
            }
            clienteDoBanco.setEmail(clienteDTO.getEmail());
        }

        if (clienteDTO.getTelefone() != null && !clienteDoBanco.getTelefone().equals(clienteDTO.getTelefone())) {
            if (repository.existsByTelefone(clienteDTO.getTelefone())) {
                throw new NumeroDeTelefoneJaCadastrado(
                        "Número de telefone já cadastrado: " + clienteDTO.getTelefone()
                );
            }
            clienteDoBanco.setTelefone(clienteDTO.getTelefone());
        }

        Cliente clienteAtualizado = repository.save(clienteDoBanco);
        return toResponseDTO(clienteAtualizado);
    }



    public void deletarUsuario(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(("Cliente não encontrado com id: " + id + ", não e possivel deletar!")));
        repository.deleteById(id);


    }
    public Page<ClienteResponseDTO> buscaPorNome(String nome, Pageable pageable) {
        Page<Cliente> page = repository.findByNomeContaining(nome, pageable);
        if (page.isEmpty()) {
            throw new NomeNaoEncontrdo("Nome: " + nome + " não encontrado");
        }
        return page.map(this::toResponseDTO);

    }



}



