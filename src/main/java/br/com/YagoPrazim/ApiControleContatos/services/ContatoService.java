package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.dtos.request.ContatoRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.ContatoResponseDto;
import br.com.YagoPrazim.ApiControleContatos.exceptions.ResourceNotFoundException;
import br.com.YagoPrazim.ApiControleContatos.mappers.ContatoMapper;
import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import br.com.YagoPrazim.ApiControleContatos.repositories.ContatoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoResponseDto listarContatoPorId(Long id) {
        ContatoModel contatoModel = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado."));

        return ContatoMapper.INSTANCE.converteParaResponseDto(contatoModel);
    }

    public ContatoResponseDto atualizarContato(Long id, ContatoRequestDto contatoRequestDto) {
        ContatoModel contatoModel = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar, contato não encontrado."));

        ContatoMapper.INSTANCE.atualizaModelAPartirDeDto(contatoRequestDto, contatoModel);
        ContatoModel contatoAtualizado = contatoRepository.save(contatoModel);

        return ContatoMapper.INSTANCE.converteParaResponseDto(contatoAtualizado);
    }

    public void deletarContato(Long id) {
        ContatoModel contatoModel = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível deletar, contato não encontrado."));

        contatoRepository.delete(contatoModel);
    }
}
