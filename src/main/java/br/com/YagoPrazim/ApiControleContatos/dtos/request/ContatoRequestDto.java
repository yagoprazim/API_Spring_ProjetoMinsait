package br.com.YagoPrazim.ApiControleContatos.dtos.request;

import br.com.YagoPrazim.ApiControleContatos.validations.constraints.TipoContato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContatoRequestDto(

        @NotNull(message = "Você deve escolher um tipo de contato ('0' - telefone OU '1' - celular).")
        @TipoContato
        Integer tipoContato,

        @NotBlank(message = "Digite um número de telefone ou celular.")
        String contato) {}
