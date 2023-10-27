package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.models.Pessoa;
import br.com.YagoPrazim.ApiControleContatos.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {
    private PessoaService pessoaService;
    @Autowired
    public PessoaController(PessoaService pessoaService){this.pessoaService = pessoaService;}

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarTodasPessoas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarTodasPessoas(paginacao));
    }



}
