package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.Dtos.MalaDiretaDto;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;
import br.com.YagoPrazim.ApiControleContatos.repositories.PessoaRepository;
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
        return pessoaRepository.findAll(paginacao);
    }

    public Optional<PessoaModel> listarPessoaPorId(Long id) {return pessoaRepository.findById(id);}

    public Optional<MalaDiretaDto> listarMalaDiretaPorId(Long id) {
        return pessoaRepository.findById(id)
            .map(pessoa -> {
                String malaDireta = pessoa.getEndereco() + " - CEP: " +
                    pessoa.getCep() + " - " +
                    pessoa.getCidade() + "/" +
                    pessoa.getUf();
                return new MalaDiretaDto(pessoa.getId(), pessoa.getNome(), malaDireta);
            });
    }

    public PessoaModel registrarPessoa(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }

    public PessoaModel atualizarPessoa(Long id, PessoaModel pessoaModel) {
        Optional<PessoaModel> pessoaEncontrada = pessoaRepository.findById(id);

        if (pessoaEncontrada.isPresent()) {
            PessoaModel pessoaAtualizada = pessoaEncontrada.get();
            pessoaAtualizada.setNome(pessoaModel.getNome());
            pessoaAtualizada.setEndereco(pessoaModel.getEndereco());
            pessoaAtualizada.setCep(pessoaModel.getCep());
            pessoaAtualizada.setCidade(pessoaModel.getCidade());
            pessoaAtualizada.setUf(pessoaModel.getUf());
            return pessoaRepository.save(pessoaAtualizada);
        }
        return null;
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }


}
