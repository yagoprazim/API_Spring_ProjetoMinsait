package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.dtos.ContatoDto;
import br.com.YagoPrazim.ApiControleContatos.services.ContatoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDto> listarContatoPorId(@PathVariable Long id) {
        ContatoDto contatoDto = contatoService.listarContatoPorId(id);

        return ResponseEntity.ok(contatoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoDto> atualizarContato(@PathVariable Long id, @RequestBody @Valid ContatoDto contatoDto){
        ContatoDto contatoAtualizado = contatoService.atualizarContato(id, contatoDto);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        contatoService.deletarContato(id);

        return ResponseEntity.noContent().build();
    }

}
