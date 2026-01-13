package com.MinhaAPIclientes.APIClentes.service;
import com.MinhaAPIclientes.APIClentes.DTO.ClienteResponseDTO;
import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import com.MinhaAPIclientes.APIClentes.exception.ClienteNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestMetodoDeletar {
    @Mock
    ClienteRepository repository;

    @InjectMocks
    ClienteService service;

    @Test
    void deveVerSedeletaComeSemoID() {

        when(repository.existsById(1L)).thenReturn(false);
        assertThrows(ClienteNaoEncontradoException.class,() -> service.deletarUsuario(1L));

    }

}
