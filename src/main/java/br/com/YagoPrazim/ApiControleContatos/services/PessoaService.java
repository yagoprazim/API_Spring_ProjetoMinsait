package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.dtos.MalaDiretaDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.request.ContatoRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.request.PessoaRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.ContatoResponseDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.PessoaResponseDto;
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

    public Page<PessoaResponseDto> listarTodasPessoas(Pageable paginacao) {
        return pessoaRepository.findAll(paginacao)
                .map(PessoaMapper.INSTANCE::converteParaResponseDto);
    }

    public PessoaResponseDto listarPessoaPorId(Long id) {
        PessoaModel pessoaModel = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));

        return PessoaMapper.INSTANCE.converteParaResponseDto(pessoaModel);
    }

    public PessoaResponseDto registrarPessoa(PessoaRequestDto pessoaRequestDto) {
        PessoaModel pessoaModel = PessoaMapper.INSTANCE.converteParaModel(pessoaRequestDto);
        PessoaModel pessoaSalva = pessoaRepository.save(pessoaModel);

        return PessoaMapper.INSTANCE.converteParaResponseDto(pessoaSalva);
    }

    public PessoaResponseDto atualizarPessoa(Long id, PessoaRequestDto pessoaRequestDto) {
        PessoaModel pessoaModel = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar, pessoa não encontrada."));

        PessoaMapper.INSTANCE.atualizaModelAPartirDeDto(pessoaRequestDto, pessoaModel);
        PessoaModel pessoaAtualizada = pessoaRepository.save(pessoaModel);

        return PessoaMapper.INSTANCE.converteParaResponseDto(pessoaAtualizada);
    }

    public void deletarPessoa(Long id) {
        PessoaModel pessoaModel = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível deletar, pessoa não encontrada."));

        pessoaRepository.delete(pessoaModel);
    }

    public MalaDiretaDto listarMalaDiretaPorId(Long id) {
        return pessoaRepository.findById(id)
                .map(PessoaMapper.INSTANCE::converteParaResponseDto)
                .map(this::construirMalaDiretaDto)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));
    }

    private MalaDiretaDto construirMalaDiretaDto(PessoaResponseDto pessoaResponseDto) {
        String malaDireta = Stream.of(
                pessoaResponseDto.endereco(), Optional.ofNullable(pessoaResponseDto.cep())
                        .map(cep -> "CEP: " + cep).orElse(null),
                        pessoaResponseDto.cidade(),
                        pessoaResponseDto.uf())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" - "));

        return new MalaDiretaDto(pessoaResponseDto.id(), pessoaResponseDto.nome(), malaDireta);
    }

    public Page<ContatoResponseDto> listarContatosPessoa(Long pessoaId, Pageable paginacao) {
        PessoaModel pessoaModel = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));

        Page<ContatoModel> contatos = contatoRepository.findByPessoa(pessoaModel, paginacao);
        if (contatos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum contato encontrado na pessoa informada.");
        }

        return contatos.map(ContatoMapper.INSTANCE::converteParaResponseDto);
    }

    public ContatoResponseDto adicionarContatoPessoa(Long pessoaId, ContatoRequestDto contatoRequestDto) {
        PessoaModel pessoaModel = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));

        ContatoModel contatoModel = ContatoMapper.INSTANCE.converteParaModel(contatoRequestDto);
        contatoModel.setPessoa(pessoaModel);
        ContatoModel contatoSalvo = contatoRepository.save(contatoModel);

        return ContatoMapper.INSTANCE.converteParaResponseDto(contatoSalvo);
    }
}
