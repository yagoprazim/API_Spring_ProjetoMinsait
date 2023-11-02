package br.com.YagoPrazim.ApiControleContatos.dtos.response;

public record ContatoResponseDto(

        Long id,

        Integer tipoContato,

        String contato,

        PessoaResponseDto pessoa) {}