package br.com.YagoPrazim.ApiControleContatos.exceptions;

import br.com.YagoPrazim.ApiControleContatos.dtos.ErroDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.ErrosListaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrosListaDto>> trataErro400(MethodArgumentNotValidException exception){
        var errosLista = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errosLista.stream().map(ErrosListaDto::new).toList());
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroDto> trataErro404(ResourceNotFoundException exception) {
        var erro = new ErroDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
