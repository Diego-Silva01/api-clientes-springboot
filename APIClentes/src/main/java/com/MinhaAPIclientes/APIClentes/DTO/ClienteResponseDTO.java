package com.MinhaAPIclientes.APIClentes.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.swing.*;
import javax.xml.crypto.Data;

@Schema(description = "Dados retornados após operação com cliente")

public record ClienteResponseDTO(
        @Schema(description = "ID do cliente", example = "1")
        Long id,
        @Schema(description = "Nome do cliemte",example = "Diego silva")
        String nome,
        @Schema(description = "Email do cliente", example = "diego@gmail.com")
        String email,
        @Schema(description = "Telefone do cliente", example = "77991122323")
        String telefone,
        @Schema(description = "Endereço do cliente", example = "RuaA, N123, Bahia")
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
