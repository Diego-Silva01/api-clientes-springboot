package com.MinhaAPIclientes.APIClentes.controller;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static  org.mockito.Mockito.*;
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
    void deveBuscaClientePorIdComSucesso() throws Exception{
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(
                1L,
                "Diego",
                "diegoa01",
                "1234",
                "iuiu"

        );
        when(service.buscarPorID(1L)).thenReturn(clienteResponseDTO);
        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Diego"))
                .andExpect(jsonPath("$.email").value("diegoa01"));

        System.out.println(clienteResponseDTO);



    }
}
