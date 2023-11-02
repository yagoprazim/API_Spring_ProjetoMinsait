package br.com.YagoPrazim.ApiControleContatos.dtos.response;

public record PessoaResponseDto(

        Long id,
        String nome,
        String endereco,
        String cep,
        String cidade,
        String uf) {}
