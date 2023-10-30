package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.dtos.MalaDiretaDto;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;
import br.com.YagoPrazim.ApiControleContatos.repositories.PessoaRepository;
import br.com.YagoPrazim.ApiControleContatos.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Page<PessoaModel> listarTodasPessoas(Pageable paginacao) {
        Page<PessoaModel> pessoas = pessoaRepository.findAll(paginacao);
        if (pessoas.isEmpty()) {
            throw new ResourceNotFoundException("Não há pessoas registradas, cadastre uma!");
        }
        return pessoas;
    }

    public PessoaModel listarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));
    }

    public MalaDiretaDto listarMalaDiretaPorId(Long id) {
        return pessoaRepository.findById(id)
            .map(pessoa -> {
                String malaDireta = pessoa.getEndereco() + " - CEP: " +
                    pessoa.getCep() + " - " +
                    pessoa.getCidade() + "/" +
                    pessoa.getUf();
                return new MalaDiretaDto(pessoa.getId(), pessoa.getNome(), malaDireta);
            }).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
    }

    public PessoaModel registrarPessoa(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }

    public PessoaModel atualizarPessoa(Long id, PessoaModel pessoaModel) {
        Optional<PessoaModel> pessoaEncontrada = pessoaRepository.findById(id);
        if (pessoaEncontrada.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível atualizar, pessoa não encontrada!");
        }
        PessoaModel pessoaAtualizada = pessoaEncontrada.get();
        pessoaAtualizada.setNome(pessoaModel.getNome());
        pessoaAtualizada.setEndereco(pessoaModel.getEndereco());
        pessoaAtualizada.setCep(pessoaModel.getCep());
        pessoaAtualizada.setCidade(pessoaModel.getCidade());
        pessoaAtualizada.setUf(pessoaModel.getUf());
        return pessoaRepository.save(pessoaAtualizada);
    }

    public void deletarPessoa(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível deletar, pessoa não encontrada!");
        }
        pessoaRepository.deleteById(id);
    }


}
