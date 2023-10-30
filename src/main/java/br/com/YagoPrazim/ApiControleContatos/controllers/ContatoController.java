package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import br.com.YagoPrazim.ApiControleContatos.services.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ContatoController {
    private final ContatoService contatoService;
    @Autowired
    public ContatoController(ContatoService contatoService){
        this.contatoService = contatoService;
    }
    @Transactional
    @PostMapping("/pessoas/{id}/contatos")
    public ResponseEntity<ContatoModel> registrarContato(@PathVariable Long id, @RequestBody @Valid ContatoModel contatoModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.registrarContato(id, contatoModel));
    }

}
