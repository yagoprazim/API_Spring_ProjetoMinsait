package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.models.Pessoa;
import br.com.YagoPrazim.ApiControleContatos.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {
    private PessoaService pessoaService;
    @Autowired
    public PessoaController(PessoaService pessoaService){this.pessoaService = pessoaService;}

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarTodasPessoas(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarTodasPessoas(paginacao));
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Pessoa> registrarPessoa(@RequestBody Pessoa pessoa){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.registrarPessoa(pessoa));
    }



}
