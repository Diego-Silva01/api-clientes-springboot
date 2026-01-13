package com.MinhaAPIclientes.APIClentes.DTO;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String endereco
) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String telefone() {
        return telefone;
    }

    @Override
    public String endereco() {
        return endereco;
    }
}
