package br.com.senac.prjint3.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroApi> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        return construir(HttpStatus.NOT_FOUND, List.of(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroApi> tratarRegraNegocio(IllegalArgumentException ex) {
        return construir(HttpStatus.BAD_REQUEST, List.of(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroApi> tratarValidacao(MethodArgumentNotValidException ex) {
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        return construir(HttpStatus.BAD_REQUEST, mensagens);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroApi> tratarIntegridade(DataIntegrityViolationException ex) {
        return construir(HttpStatus.CONFLICT, List.of("Violação de integridade no banco. Verifique CPF, e-mail, chaves estrangeiras e registros duplicados."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroApi> tratarErroGeral(Exception ex) {
        return construir(HttpStatus.INTERNAL_SERVER_ERROR, List.of("Erro interno: " + ex.getMessage()));
    }

    private ResponseEntity<ErroApi> construir(HttpStatus status, List<String> mensagens) {
        ErroApi erro = new ErroApi(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensagens
        );
        return ResponseEntity.status(status).body(erro);
    }
}
