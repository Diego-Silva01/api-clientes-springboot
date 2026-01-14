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
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    ClienteRepository repository;

    @InjectMocks
    ClienteService service;

    /* ===================== TESTE SALVAR ===================== */

    @Test
    void deveSalvarClienteComSucesso() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Diego");
        dto.setEmail("diego@email.com");
        dto.setTelefone("123");
        dto.setEndereco("Rua A");

        when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repository.existsByTelefone(dto.getTelefone())).thenReturn(false);

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setId(1L);
        clienteSalvo.setNome(dto.getNome());
        clienteSalvo.setEmail(dto.getEmail());
        clienteSalvo.setTelefone(dto.getTelefone());
        clienteSalvo.setEndereco(dto.getEndereco());

        when(repository.save(any(Cliente.class))).thenReturn(clienteSalvo);

        ClienteResponseDTO response = service.salvar(dto);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Diego", response.nome());
    }

    /* ===================== TESTE BUSCAR POR ID ===================== */

    @Test
    void deveBuscarClientePorIdComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Diego");
        cliente.setEmail("diego@email.com");

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteResponseDTO response = service.buscarPorID(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Diego", response.nome());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.buscarPorID(1L));
    }

    /* ===================== TESTE BUSCAR PAGINADO ===================== */

    @Test
    void deveBuscarClientesPaginados() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Diego");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Cliente> page = new PageImpl<>(List.of(cliente));

        when(repository.findAll(pageable)).thenReturn(page);

        Page<ClienteResponseDTO> response =
                service.buscarTodosPaginado(pageable, null);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals("Diego", response.getContent().get(0).nome());
    }

    /* ===================== TESTE DELETAR ===================== */

    @Test
    void deveDeletarClienteComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        assertDoesNotThrow(() -> service.deletarUsuario(1L));

        verify(repository).delete(cliente);
    }
}

