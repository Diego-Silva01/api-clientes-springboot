package com.MinhaAPIclientes.APIClentes.exception;
// extends RuntimeException →
//
//Diz ao Java: “isso é um erro”
//
//Diz ao Spring: “se isso acontecer, capture”

//Uma classe de erro personalizada


public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
