package com.MinhaAPIclientes.APIClentes.exception;

public class NumeroDeTelefoneJaCadastrado  extends RuntimeException{
    public NumeroDeTelefoneJaCadastrado(String mensagemErroTelefone) {
        super(mensagemErroTelefone);
    }

}
