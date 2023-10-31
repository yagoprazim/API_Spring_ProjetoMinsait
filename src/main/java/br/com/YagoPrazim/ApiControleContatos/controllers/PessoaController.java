package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.dtos.ContatoDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.MalaDiretaDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.PessoaDto;
import br.com.YagoPrazim.ApiControleContatos.services.PessoaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<PessoaDto>> listarTodasPessoas(@PageableDefault @ParameterObject Pageable paginacao) {
        Page<PessoaDto> pessoaDto = pessoaService.listarTodasPessoas(paginacao);

        return ResponseEntity.ok(pessoaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> listarPessoaPorId(@PathVariable Long id) {
        PessoaDto pessoaDto = pessoaService.listarPessoaPorId(id);

        return ResponseEntity.ok(pessoaDto);
    }

    @GetMapping("/maladireta/{id}")
    public ResponseEntity<MalaDiretaDto> listarMalaDiretaPorId(@PathVariable Long id) {
        MalaDiretaDto malaDiretaDto = pessoaService.listarMalaDiretaPorId(id);

        return ResponseEntity.ok(malaDiretaDto);
    }

    @PostMapping
    public ResponseEntity<PessoaDto> registrarPessoa(@RequestBody @Valid PessoaDto pessoaDto) {
        PessoaDto pessoaRegistrada = pessoaService.registrarPessoa(pessoaDto);

        return ResponseEntity.ok(pessoaRegistrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> atualizarPessoa(@PathVariable Long id, @Valid @RequestBody PessoaDto pessoaDto) {
        PessoaDto pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoaDto);

        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/contatos")
    public ResponseEntity<ContatoDto> adicionarContato(@PathVariable Long id, @RequestBody @Valid ContatoDto contatoDto) {
        ContatoDto contatoAdicionado = pessoaService.adicionarContato(id, contatoDto);

        return ResponseEntity.ok(contatoAdicionado);
    }

    @GetMapping("/{idPessoa}/contatos")
    public ResponseEntity<Page<ContatoDto>> listarContatos(@PathVariable Long idPessoa, @PageableDefault @ParameterObject Pageable paginacao) {

        Page<ContatoDto> contatosDtoPage = pessoaService.listarContatos(idPessoa, paginacao);
        return ResponseEntity.ok(contatosDtoPage);
    }
}
