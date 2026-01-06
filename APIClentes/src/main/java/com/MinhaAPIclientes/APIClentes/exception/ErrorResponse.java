package com.MinhaAPIclientes.APIClentes.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
    private List<ValidationError> erros;

    public List<ValidationError> getErros() {
        return erros;
    }


    public ErrorResponse(int status, String mensagem, List<ValidationError> erros) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
        this.erros = erros;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setErros(List<ValidationError> erros) {
        this.erros = erros;
    }

}
