package br.com.YagoPrazim.ApiControleContatos.mappers;

import br.com.YagoPrazim.ApiControleContatos.dtos.request.PessoaRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.PessoaResponseDto;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    PessoaResponseDto converteParaResponseDto(PessoaModel pessoaModel);

    PessoaModel converteParaModel(PessoaRequestDto pessoaRequestDto);

    @Mapping(target = "id", ignore = true)
    void atualizaModelAPartirDeDto(PessoaRequestDto pessoaRequestDto, @MappingTarget PessoaModel pessoaModel);
}