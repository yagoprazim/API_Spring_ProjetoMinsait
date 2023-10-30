package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;
import br.com.YagoPrazim.ApiControleContatos.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;
    @Autowired
    public ContatoService(ContatoRepository contatoRepository, PessoaService pessoaService){
        this.contatoRepository = contatoRepository;
        this.pessoaService = pessoaService;
    }

    public ContatoModel registrarContato(Long id, ContatoModel contatoModel) {
        PessoaModel pessoa = pessoaService.listarPessoaPorId(id);
        contatoModel.setPessoa(pessoa);
        return contatoRepository.save(contatoModel);
    }

}
