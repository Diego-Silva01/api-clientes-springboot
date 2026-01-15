package com.MinhaAPIclientes.APIClentes.controller;

import com.MinhaAPIclientes.APIClentes.DTO.ClienteDTO;
import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static  org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ClienteController.class)
public class ClineteControllerBuscaPagindo {
    @MockBean
    private ClienteService clienteService;

    @Autowired
    MockMvc mockMvc;
    @Test
    void buscaTodos()throws Exception{
        ClienteResponseDTO cliente = new ClienteResponseDTO(
                1L,
                "Diego",
                "Diegoff",
                "123",
                "iuiu"
        );

        Pageable pageable = PageRequest.of(0, 10);
        Page<ClienteResponseDTO> clienteFake = new PageImpl<>(List.of(cliente));
        when(clienteService.buscarTodosPaginado(any(Pageable.class), isNull())).thenReturn(clienteFake);

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
            .andExpect(jsonPath("$.content[0].nome").value("Diego"));
    }
}
