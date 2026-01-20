package com.MinhaAPIclientes.APIClentes.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;
@Schema(description = "Resposta padrão de erro da API")
public class ErroResponseDTO {
@Schema(example = "2026-01-20:09:14")
    private LocalDateTime timestamp;
    @Schema(example = "404")
    private int status;
    @Schema(description = "Mensagem de erro")
    private String erro;
    @Schema(description = "O que aconteceu", example = "Cliente não encontrado")
    private String mensagem;
    @Schema(example = "/clientes/1")
    private String path;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getPath() {
        return path;
    }

    public ErroResponseDTO(int status, String erro, String mensagem, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
    }

    // getters
}
