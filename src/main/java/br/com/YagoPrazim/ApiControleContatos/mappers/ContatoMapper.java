package br.com.YagoPrazim.ApiControleContatos.mappers;

import br.com.YagoPrazim.ApiControleContatos.dtos.request.ContatoRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.ContatoResponseDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.PessoaResponseDto;
import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContatoMapper {

    ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

    ContatoResponseDto converteParaResponseDto(ContatoModel contatoModel);

    ContatoModel converteParaModel(ContatoRequestDto contatoRequestDto);

    @Mapping(target = "id", ignore = true)
    void atualizaModelAPartirDeDto(ContatoRequestDto contatoRequestDto, @MappingTarget ContatoModel contatoModel);

    default PessoaResponseDto converteParaPessoaResponseDto(PessoaModel pessoaModel) {
        return PessoaMapper.INSTANCE.converteParaResponseDto(pessoaModel);
    }
}
