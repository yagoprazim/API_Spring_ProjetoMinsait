package br.com.YagoPrazim.ApiControleContatos.validations;

import br.com.YagoPrazim.ApiControleContatos.dtos.DadosValidacaoErrosDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratamentoErrosValidation {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosValidacaoErrosDto>> tratarErro400(MethodArgumentNotValidException exception){
        var listaErros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(listaErros.stream().map(DadosValidacaoErrosDto::new).toList());
    }



}
