package com.MinhaAPIclientes.APIClentes.exception;

import java.beans.XMLEncoder;
import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse {

    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
    private List<FieldErrorResponse> erros;
    public ValidationErrorResponse(int status) {
        this.status = status;
    }
    public List<FieldErrorResponse> getErros() {
        return erros;
    }


    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }



    public int getStatus() {
        return status;
    }

public void adicionarErro(String campo, String mensagem){
        erros.add(new FieldErrorResponse(campo, mensagem));

}
}
