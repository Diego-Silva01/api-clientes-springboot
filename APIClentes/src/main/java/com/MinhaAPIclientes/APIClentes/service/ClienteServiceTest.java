package com.MinhaAPIclientes.APIClentes.service;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
    when(repository.save(any(Cliente.class))).thenReturn(clienteSalvo);
    ClienteResponseDTO response = service.salvar(clienteDTO);
    assertEquals(1L, response.id());
    assertEquals("Diego", response.nome());
    assertEquals("Diefodd", response.email());
    assertEquals("123", response.telefone());
    assertEquals("iuiu", response.endereco());

    }

    @ExtendWith(MockitoExtension.class)
    public static class ClinteTestBuscaPaginado {
        @Mock
        ClienteRepository repository;

        @InjectMocks
        ClienteService service;

        @Test
        void TestaBuscaTodoPaginados(){
            Cliente clienteBanco = new Cliente();
            clienteBanco.setId(1L);
            clienteBanco.setNome("Diego");
            clienteBanco.setEmail("diegoffa01");
            clienteBanco.setTelefone("77991329279");
            clienteBanco.setEndereco("ijiu");
            Pageable pageable = PageRequest.of(0, 10);
            Page<Cliente> pageFake = new PageImpl<>(List.of(clienteBanco));
            when(repository.findAll(pageable)).thenReturn(pageFake);
            Page<ClienteResponseDTO> clienteResponseDTOS = service.buscarTodosPaginado(pageable, null);
            assertNotNull(clienteResponseDTOS);
            assertEquals(1,clienteResponseDTOS.getTotalElements());
            assertEquals("Diego", clienteResponseDTOS.getContent().get(0).nome());


            }
        }

    @ExtendWith(MockitoExtension.class)
    public static class TestMetodoDeletar {
        @Mock
        ClienteRepository repository;

        @InjectMocks
        ClienteService service;

        @Test
        void deveVerSedeletaComeSemoID() {
            Cliente cliente = new Cliente();
            cliente.setId(1L);

            when(repository.findById(1L)).thenReturn(Optional.of(cliente));;
            assertDoesNotThrow(()-> service.deletarUsuario(1L));
            verify(repository).delete(cliente);

        }

    }

    @ExtendWith(MockitoExtension.class)
    public static class ClienteTestRepositoryEncontraCliente {
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
}





