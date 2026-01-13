package com.MinhaAPIclientes.APIClentes.service;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteTestRepositoryEncontraCliente {
    @Mock
    ClienteRepository repository;
    @InjectMocks
    ClienteService service;

    @Test
    void DeveVerSeOClienteFoiEncontrado() {

        Cliente clienteBanco = new Cliente();
        clienteBanco.setId(1L);
        clienteBanco.setNome("Diego");
        clienteBanco.setEmail("diegoffa01");
        clienteBanco.setTelefone("77991329279");
        clienteBanco.setEndereco("ijiu");

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        ClienteResponseDTO responseDTO = service.buscarPorID(1L);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO);
        assertEquals(1L, responseDTO.id());
        assertEquals("Diego", responseDTO.nome());
        assertEquals("diegoffa01", responseDTO.email());
    }
}
