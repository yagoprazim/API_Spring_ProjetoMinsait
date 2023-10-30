package br.com.YagoPrazim.ApiControleContatos.dtos;

import org.springframework.validation.FieldError;

public record DadosValidacaoErrosDto(String campo, String erro) {

    public DadosValidacaoErrosDto(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
