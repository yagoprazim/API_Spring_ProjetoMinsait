package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.Dtos.MalaDiretaDto;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;
import br.com.YagoPrazim.ApiControleContatos.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {
    private PessoaService pessoaService;
    @Autowired
    public PessoaController(PessoaService pessoaService){this.pessoaService = pessoaService;}

    @GetMapping
    public ResponseEntity<Page<PessoaModel>> listarTodasPessoas(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarTodasPessoas(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PessoaModel>> listarPessoaPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPessoaPorId(id));
    }

    @GetMapping("/maladireta/{id}")
    public ResponseEntity<Optional<MalaDiretaDto>> listarMalaDiretaPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarMalaDiretaPorId(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PessoaModel> registrarPessoa(@RequestBody PessoaModel pessoaModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.registrarPessoa(pessoaModel));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaModel pessoaModel) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.atualizarPessoa(id, pessoaModel));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable Long id){
        pessoaService.deletarPessoa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
