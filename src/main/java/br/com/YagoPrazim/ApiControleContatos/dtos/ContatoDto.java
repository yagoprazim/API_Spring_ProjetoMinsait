package br.com.YagoPrazim.ApiControleContatos.dtos;

import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;

public record ContatoDto(Long id, Integer tipoContato, String contato, PessoaModel pessoa) {

}
