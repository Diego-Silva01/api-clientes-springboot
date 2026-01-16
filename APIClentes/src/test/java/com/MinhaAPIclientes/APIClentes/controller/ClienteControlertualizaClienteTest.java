package com.MinhaAPIclientes.APIClentes.controller;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static  org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClienteController.class)
class ClienteControllerAtualizarTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Test
    void deveAtualizarClienteComSucesso() throws Exception {

        ClienteResponseDTO response =
                new ClienteResponseDTO(
                        1L,
                        "Diego",
                        "diegoa01@gmail.com",
                        "1234",
                        "iuiu"
                );

        when(service.atualizarCliente(anyLong(), any(ClienteDTO.class)))
                .thenReturn(response);

        mockMvc.perform(
                        put("/clientes/1")
                                .contentType("application/json")
                                .content("""
                        {
                          "nome": "Diego",
                          "email": "diegoa01@gmail.com",
                          "telefone": "1234",
                          "endereco": "iuiu"
                        }
                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Diego"));
    }
}

