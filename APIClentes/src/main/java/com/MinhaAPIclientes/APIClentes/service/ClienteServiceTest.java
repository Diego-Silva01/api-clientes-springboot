package com.MinhaAPIclientes.APIClentes.service;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    ClienteRepository repository;
    @InjectMocks
    ClienteService service;


    ClienteDTO clienteDTO = new ClienteDTO();
    @Test
    void deveSalvarClienteComSucesso(){
        clienteDTO.setNome("Diego");
        clienteDTO.setEmail("Diefodd");
        clienteDTO.setTelefone("123");
        clienteDTO.setEndereco("iuiu");
        when(repository.existsByEmail(clienteDTO.getEmail())).thenReturn(false);
        when(repository.existsByTelefone(clienteDTO.getTelefone())).thenReturn(false);

    Cliente clienteSalvo = new Cliente();
    clienteSalvo.setId(1L);
    clienteSalvo.setNome(clienteDTO.getNome());
    clienteSalvo.setEmail(clienteDTO.getEmail());
    clienteSalvo.setTelefone(clienteDTO.getTelefone());
    clienteSalvo.setEndereco(clienteDTO.getEndereco());
    when(repository.existsByEmail(clienteSalvo.getEmail())).thenReturn(true);
    ClienteResponseDTO response = service.salvar(clienteDTO);
    assertEquals(1L, response.id());
    assertEquals("Diego", response.nome());
    assertEquals("Diefodd", response.email());
    assertEquals("123", response.telefone());
    assertEquals("iuiu", response.endereco());

    }

}





