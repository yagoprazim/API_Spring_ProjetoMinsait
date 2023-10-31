package br.com.YagoPrazim.ApiControleContatos.mappers;

import br.com.YagoPrazim.ApiControleContatos.dtos.PessoaDto;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    PessoaDto toDto(PessoaModel pessoaModel);

    PessoaModel toModel(PessoaDto pessoaDto);

    @Mapping(target = "id", ignore = true)
    void updateModelFromDto(PessoaDto dto, @MappingTarget PessoaModel entity);
}

