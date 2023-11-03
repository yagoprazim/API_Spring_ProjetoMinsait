package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.dtos.request.ContatoRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.MalaDiretaDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.request.PessoaRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.ContatoResponseDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.PessoaResponseDto;
import br.com.YagoPrazim.ApiControleContatos.services.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Operation(summary = "Lista todas as pessoas registradas")
    @GetMapping
    public ResponseEntity<Page<PessoaResponseDto>> listarTodasPessoas(@PageableDefault @ParameterObject Pageable paginacao) {
        Page<PessoaResponseDto> pessoa = pessoaService.listarTodasPessoas(paginacao);

        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Lista uma pessoa específica pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> listarPessoaPorId(@PathVariable Long id) {
        PessoaResponseDto pessoa = pessoaService.listarPessoaPorId(id);

        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Registra uma pessoa.")
    @PostMapping
    public ResponseEntity<PessoaResponseDto> registrarPessoa(@RequestBody @Valid PessoaRequestDto pessoaRequestDto) {
        PessoaResponseDto pessoaRegistrada = pessoaService.registrarPessoa(pessoaRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRegistrada);
    }

    @Operation(summary = "Atualiza uma pessoa específica pelo ID.")
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> atualizarPessoa(@PathVariable Long id,
                                                             @RequestBody @Valid PessoaRequestDto pessoaRequestDto) {
        PessoaResponseDto pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoaRequestDto);

        return ResponseEntity.ok(pessoaAtualizada);
    }

    @Operation(summary = "Exclui uma pessoa específica pelo ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista uma pessoa específica concatenando os dados de endereço, ID da pessoa como parâmetro.")
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<MalaDiretaDto> listarMalaDiretaPorId(@PathVariable Long id) {
        MalaDiretaDto malaDiretaDto = pessoaService.listarMalaDiretaPorId(id);

        return ResponseEntity.ok(malaDiretaDto);
    }

    @Operation(summary = "Lista todos os contatos de uma pessoa específica, ID da pessoa como parâmetro.")
    @GetMapping("/{idPessoa}/contatos")
    public ResponseEntity<Page<ContatoResponseDto>> listarContatosPessoa(@PathVariable Long idPessoa,
                                                                         @PageableDefault @ParameterObject Pageable paginacao) {
        Page<ContatoResponseDto> contatosDtoPage = pessoaService.listarContatosPessoa(idPessoa, paginacao);

        return ResponseEntity.ok(contatosDtoPage);
    }

    @Operation(summary = "Cria um contato para uma pessoa específica, ID da pessoa como parâmetro.")
    @PostMapping("/{id}/contatos")
    public ResponseEntity<ContatoResponseDto> adicionarContatoPessoa(@PathVariable Long id,
                                                                     @RequestBody @Valid ContatoRequestDto contatoRequestDto) {
        ContatoResponseDto contatoAdicionado = pessoaService.adicionarContatoPessoa(id, contatoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(contatoAdicionado);
    }
}
