package br.com.YagoPrazim.ApiControleContatos.dtos;

import jakarta.validation.constraints.NotBlank;

public record PessoaDto(

    Long id,

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    String nome,

    String endereco,

    String cep,

    String cidade,

    String uf) {

}
