package com.MinhaAPIclientes.APIClentes.exception;
import com.MinhaAPIclientes.APIClentes.exception.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  ERRO DE VALIDAÇÃO (@Valid)
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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleClienteNaoEncontrado(
            ClienteNaoEncontradoException ex) {

        ErrorResponse erro = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErrorResponse> handlerEmailJaCadastrado(EmailJaCadastradoException ex){

        ErrorResponse erro = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
    @ExceptionHandler(NumeroDeTelefoneJaCadastrado.class)
    public ResponseEntity<ErrorResponse> handleTelefoneJaCadastrado(
            NumeroDeTelefoneJaCadastrado ex){
        ErrorResponse erro = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);

    }
    @ExceptionHandler(NomeNaoEncontrdo.class)
    public ResponseEntity<ErrorResponse>handleNomeNaoEncontrado(NomeNaoEncontrdo ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }



}
