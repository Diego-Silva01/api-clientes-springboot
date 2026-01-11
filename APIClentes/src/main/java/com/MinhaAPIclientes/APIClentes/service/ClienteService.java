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


    public ClienteResponseDTO salvar(ClienteDTO dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException(
                    "E-mail já cadastrado: " + dto.getEmail()
            );
        }

        if (repository.existsByTelefone(dto.getTelefone())) {
            throw new NumeroDeTelefoneJaCadastrado(
                    "Número de telefone já cadastrado: " + dto.getTelefone()
            );
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        Cliente clienteSalvo = repository.save(cliente);

        return toResponseDTO(clienteSalvo);

    }

    /// Pageable = pedido
    ///
    /// Page = resposta
    public Page<ClienteResponseDTO> buscarTodosPaginado(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponseDTO);
    }



    public ClienteResponseDTO buscarPorID(Long id) {
        return repository.findById(id).map(this::toResponseDTO)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id));

    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente clientedoBanco = repository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id));
        clientedoBanco.setNome(clienteDTO.getNome());
        clientedoBanco.setEmail(clienteDTO.getEmail());
        clientedoBanco.setTelefone(clienteDTO.getTelefone());
        clientedoBanco.setEndereco(clienteDTO.getEndereco());
        Cliente clienteAtualizado = repository.save(clientedoBanco);
       return toResponseDTO(clientedoBanco);
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



