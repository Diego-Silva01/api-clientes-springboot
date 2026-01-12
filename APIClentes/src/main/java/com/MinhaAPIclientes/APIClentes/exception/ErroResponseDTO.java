package com.MinhaAPIclientes.APIClentes.exception;

import java.time.LocalDateTime;

public class ErroResponseDTO {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
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
