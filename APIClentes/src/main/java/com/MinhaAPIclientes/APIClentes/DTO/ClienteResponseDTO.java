package com.MinhaAPIclientes.APIClentes.DTO;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String endereco
) {}
