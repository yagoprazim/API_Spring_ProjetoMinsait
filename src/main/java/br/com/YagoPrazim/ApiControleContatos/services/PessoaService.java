package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.models.Pessoa;
import br.com.YagoPrazim.ApiControleContatos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Page<Pessoa> listarTodasPessoas(Pageable paginacao) {
        return pessoaRepository.findAll(paginacao);
    }

    public Pessoa registrarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }




}
