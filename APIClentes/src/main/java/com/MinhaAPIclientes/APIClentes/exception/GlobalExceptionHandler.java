package com.MinhaAPIclientes.APIClentes.exception;

import com.MinhaAPIclientes.APIClentes.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  ERRO DE VALIDAÇÃO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidacao(
            MethodArgumentNotValidException ex) {

        ValidationErrorResponse erro =
                new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value());

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            erro.adicionarErro(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        });

        return ResponseEntity.badRequest().body(erro);
    }

    //  CLIENTE NÃO ENCONTRADO
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> handleClienteNaoEncontrado(
            ClienteNaoEncontradoException ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    //  EMAIL JÁ CADASTRADO
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErroResponseDTO> handleEmailJaCadastrado(
            EmailJaCadastradoException ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    //  TELEFONE JÁ CADASTRADO
    @ExceptionHandler(NumeroDeTelefoneJaCadastrado.class)
    public ResponseEntity<ErroResponseDTO> handleTelefoneJaCadastrado(
            NumeroDeTelefoneJaCadastrado ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    //  NOME NÃO ENCONTRADO
    @ExceptionHandler(NomeNaoEncontrdo.class)
    public ResponseEntity<ErroResponseDTO> handleNomeNaoEncontrado(
            NomeNaoEncontrdo ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }
    private ResponseEntity<ErroResponseDTO> buildError(
            HttpStatus status,
            String mensagem,
            HttpServletRequest request) {

        ErroResponseDTO erro = new ErroResponseDTO(
                status.value(),
                status.name(),
                mensagem,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(erro);
    }
}
