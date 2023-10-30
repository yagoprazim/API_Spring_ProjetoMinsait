package br.com.YagoPrazim.ApiControleContatos.dtos;

import org.springframework.validation.FieldError;

public record ErrosListaDto(String campo, String erro) {
    public ErrosListaDto(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
