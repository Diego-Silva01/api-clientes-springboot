package com.MinhaAPIclientes.APIClentes.controller;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Test
    @DisplayName("Deve buscar cliente por ID com sucesso")
    void deveBuscaClientePorIdComSucesso() throws Exception {
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(1L, "Diego", "diegoa01", "1234", "iuiu");

        when(service.buscarPorID(1L)).thenReturn(clienteResponseDTO);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Diego"));
    }

    @Nested
    @DisplayName("Testes de Deleção")
    class ClienteDelete {
        @Test
        void deveDeletarComSucesso() throws Exception {
            doNothing().when(service).deletarUsuario(1L);

            mockMvc.perform(delete("/clientes/1"))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("Testes de Paginação")
    class ClienteListagem {
        @Test
        void deveBuscarTodosPaginado() throws Exception {
            ClienteResponseDTO cliente = new ClienteResponseDTO(1L, "Diego", "Diegoff", "123", "iuiu");
            Page<ClienteResponseDTO> page = new PageImpl<>(List.of(cliente));

            when(service.buscarTodosPaginado(any(Pageable.class), any())).thenReturn(page);

            mockMvc.perform(get("/clientes"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].nome").value("Diego"));
        }
    }

    @Nested
    @DisplayName("Testes de Atualização")
    class ClienteAtualizacao {
        @Test
        void deveAtualizarClienteComSucesso() throws Exception {
            ClienteResponseDTO response = new ClienteResponseDTO(1L, "Diego", "diego@email.com", "123", "iuiu");

            when(service.atualizarCliente(anyLong(), any(ClienteDTO.class))).thenReturn(response);

            String jsonCorreto = "{\"nome\": \"Diego\", \"email\": \"diego@email.com\", \"telefone\": \"123\", \"endereco\": \"iuiu\"}";

            mockMvc.perform(put("/clientes/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonCorreto))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nome").value("Diego"));
        }
    }
}