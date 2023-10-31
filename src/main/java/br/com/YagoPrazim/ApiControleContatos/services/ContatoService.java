package br.com.YagoPrazim.ApiControleContatos.services;

import br.com.YagoPrazim.ApiControleContatos.dtos.ContatoDto;
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

    public ContatoDto listarContatoPorId(Long id) {
        ContatoModel contatoModel = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com o ID: " + id));

        return ContatoMapper.INSTANCE.toDto(contatoModel);
    }

    public ContatoDto atualizarContato(Long id, ContatoDto contatoDto) {
        ContatoModel contatoModel = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar, contato não encontrado."));

        ContatoMapper.INSTANCE.updateModelFromDto(contatoDto, contatoModel);

        ContatoModel contatoAtualizado = contatoRepository.save(contatoModel);

        return ContatoMapper.INSTANCE.toDto(contatoAtualizado);
    }

    public void deletarContato(Long id) {
        ContatoModel contatoModel = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível deletar, contato não encontrado."));

        contatoRepository.delete(contatoModel);
    }

}
