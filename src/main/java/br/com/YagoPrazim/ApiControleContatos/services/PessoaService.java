package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;
import br.com.YagoPrazim.ApiControleContatos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Page<PessoaModel> listarTodasPessoas(Pageable paginacao) {
        return pessoaRepository.findAll(paginacao);
    }

    public Optional<PessoaModel> listarUmaPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

    public PessoaModel registrarPessoa(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }




}
