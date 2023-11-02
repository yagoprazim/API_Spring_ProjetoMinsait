package br.com.YagoPrazim.ApiControleContatos.mappers;

import br.com.YagoPrazim.ApiControleContatos.dtos.request.ContatoRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.ContatoResponseDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.PessoaResponseDto;
import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;

import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContatoMapper {

    ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

    // Método para converter de ContatoModel para ContatoResponseDto
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "tipoContato", target = "tipoContato"),
            @Mapping(source = "contato", target = "contato"),
            @Mapping(source = "pessoa", target = "pessoa")
    })
    ContatoResponseDto toResponseDto(ContatoModel contatoModel);

    // Método para converter de ContatoRequestDto para ContatoModel
    @Mappings({
            @Mapping(source = "tipoContato", target = "tipoContato"),
            @Mapping(source = "contato", target = "contato")
    })
    ContatoModel toModel(ContatoRequestDto contatoRequestDto);

    // Método para atualizar um ContatoModel existente com dados de um ContatoRequestDto
    @Mapping(target = "id", ignore = true)
    void updateModelFromDto(ContatoRequestDto contatoRequestDto, @MappingTarget ContatoModel contatoModel);

    default PessoaResponseDto convertToPessoaResponseDto(PessoaModel pessoaModel) {
        return PessoaMapper.INSTANCE.toResponseDto(pessoaModel);
    }
}
