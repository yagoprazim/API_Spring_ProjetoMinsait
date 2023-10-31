package br.com.YagoPrazim.ApiControleContatos.dtos;


import br.com.YagoPrazim.ApiControleContatos.validations.constraints.TipoContato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContatoDto(Long id,
                         @NotNull(message = "Você deve escolher um tipo de contato.")
                         @TipoContato
                         Integer tipoContato,

                         @NotBlank(message = "Digite um número de telefone ou celular.")
                         String contato,

                         Long pessoaId) {}

