package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.dtos.ContatoDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.MalaDiretaDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.PessoaDto;
import br.com.YagoPrazim.ApiControleContatos.mappers.ContatoMapper;
import br.com.YagoPrazim.ApiControleContatos.mappers.PessoaMapper;
import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;
import br.com.YagoPrazim.ApiControleContatos.repositories.ContatoRepository;
import br.com.YagoPrazim.ApiControleContatos.repositories.PessoaRepository;
import br.com.YagoPrazim.ApiControleContatos.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ContatoRepository contatoRepository;

    public Page<PessoaDto> listarTodasPessoas(Pageable paginacao) {
        return pessoaRepository.findAll(paginacao)
                .map(PessoaMapper.INSTANCE::toDto);
    }

    public PessoaDto listarPessoaPorId(Long id) {
        PessoaModel pessoaModel = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));

        return PessoaMapper.INSTANCE.toDto(pessoaModel);
    }

    public MalaDiretaDto listarMalaDiretaPorId(Long id) {
        return pessoaRepository.findById(id)
                .map(PessoaMapper.INSTANCE::toDto)
                .map(this::construirMalaDiretaDto)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));
    }

    private MalaDiretaDto construirMalaDiretaDto(PessoaDto pessoaDto) {
        String malaDireta = Stream.of(
                pessoaDto.endereco(), Optional.ofNullable(pessoaDto.cep())
                .map(cep -> "CEP: " + cep).orElse(null),
                        pessoaDto.cidade(),
                        pessoaDto.uf())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" - "));

        return new MalaDiretaDto(pessoaDto.id(), pessoaDto.nome(), malaDireta);
    }

    public PessoaDto registrarPessoa(PessoaDto pessoaDto) {
        PessoaModel pessoaModel = PessoaMapper.INSTANCE.toModel(pessoaDto);
        PessoaModel pessoaSalva = pessoaRepository.save(pessoaModel);

        return PessoaMapper.INSTANCE.toDto(pessoaSalva);
    }

    public PessoaDto atualizarPessoa(Long id, PessoaDto pessoaDto) {
        PessoaModel pessoaModel = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar, pessoa não encontrada."));

        PessoaMapper.INSTANCE.updateModelFromDto(pessoaDto, pessoaModel);
        PessoaModel pessoaAtualizada = pessoaRepository.save(pessoaModel);

        return PessoaMapper.INSTANCE.toDto(pessoaAtualizada);
    }

    public void deletarPessoa(Long id) {
        PessoaModel pessoaModel = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível deletar, pessoa não encontrada."));

        pessoaRepository.delete(pessoaModel);
    }

    //SERVICES QUE SE RELACIONAM COM CONTATOS:
    //----------------------------------------------------------------------------------------------------------
    public Page<ContatoDto> listarContatosPessoa(Long pessoaId, Pageable paginacao) {
        PessoaModel pessoaModel = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));

        Page<ContatoModel> contatosModel = contatoRepository.findByPessoa(pessoaModel, paginacao);
        if (contatosModel.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum contato encontrado para a pessoa com ID: " + pessoaId);
        }

        return contatosModel.map(ContatoMapper.INSTANCE::toDto);
    }

    public ContatoDto adicionarContatoPessoa(Long pessoaId, ContatoDto contatoDto) {
        PessoaModel pessoaModel = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));

        ContatoModel contatoModel = ContatoMapper.INSTANCE.toModel(contatoDto);
        contatoModel.setPessoa(pessoaModel);
        ContatoModel contatoSalvo = contatoRepository.save(contatoModel);

        return ContatoMapper.INSTANCE.toDto(contatoSalvo);
    }
    //----------------------------------------------------------------------------------------------------------

}
