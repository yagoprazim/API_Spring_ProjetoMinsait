package br.com.YagoPrazim.ApiControleContatos.mappers;

import br.com.YagoPrazim.ApiControleContatos.dtos.request.PessoaRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.PessoaResponseDto;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    // Método para converter PessoaModel para PessoaResponseDto
    PessoaResponseDto toResponseDto(PessoaModel pessoaModel);

    // Método para converter PessoaRequestDto para PessoaModel
    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "endereco", target = "endereco"),
            @Mapping(source = "cep", target = "cep"),
            @Mapping(source = "cidade", target = "cidade"),
            @Mapping(source = "uf", target = "uf")
    })
    PessoaModel toModel(PessoaRequestDto pessoaRequestDto);

    // Método para atualizar uma PessoaModel existente com dados de um PessoaRequestDto
    @Mapping(target = "id", ignore = true)
    void updateModelFromDto(PessoaRequestDto pessoaRequestDto, @MappingTarget PessoaModel pessoaModel);
}