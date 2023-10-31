package br.com.YagoPrazim.ApiControleContatos.mappers;

import br.com.YagoPrazim.ApiControleContatos.dtos.ContatoDto;
import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContatoMapper {

    ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

    @Mapping(source = "pessoa.id", target = "pessoaId")
    ContatoDto toDto(ContatoModel contatoModel);

    ContatoModel toModel(ContatoDto contatoDto);

    @Mapping(target = "id", ignore = true)
    void updateModelFromDto(ContatoDto dto, @MappingTarget ContatoModel entity);
}
