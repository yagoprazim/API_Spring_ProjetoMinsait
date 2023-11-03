package br.com.YagoPrazim.ApiControleContatos.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDto(

        @NotBlank(message = "O campo 'nome' é obrigatório.")
        String nome,

        String endereco,

        String cep,

        String cidade,

        String uf) {}
