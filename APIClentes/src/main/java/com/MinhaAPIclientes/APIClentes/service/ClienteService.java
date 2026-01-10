package com.MinhaAPIclientes.APIClentes.service;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
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

    public Cliente salvar(ClienteDTO dto) {

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

        return repository.save(cliente);

    }

    /// Pageable = pedido
    ///
    /// Page = resposta
    public Page<Cliente> buscarTodosPaginado(Pageable pageable) {
        return repository.findAll(pageable);
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
    public Page<Cliente> buscaPorNome(String nome, Pageable pageable) {
        Page<Cliente> page = repository.findByNomeContaining(nome, pageable);

        if (page.isEmpty()) {
            throw new NomeNaoEncontrdo("Nome: " + nome + " não encontrado");
        }

        return page;
    }


}



