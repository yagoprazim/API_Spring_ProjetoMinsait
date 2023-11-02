package br.com.YagoPrazim.ApiControleContatos.controllers;

import br.com.YagoPrazim.ApiControleContatos.dtos.request.ContatoRequestDto;
import br.com.YagoPrazim.ApiControleContatos.dtos.response.ContatoResponseDto;
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
    public ResponseEntity<ContatoResponseDto> listarContatoPorId(@PathVariable Long id) {
        ContatoResponseDto contato = contatoService.listarContatoPorId(id);

        return ResponseEntity.ok(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDto> atualizarContato(@PathVariable Long id, @RequestBody @Valid ContatoRequestDto contatoRequestDto){
        ContatoResponseDto contatoAtualizado = contatoService.atualizarContato(id, contatoRequestDto);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        contatoService.deletarContato(id);

        return ResponseEntity.noContent().build();
    }
}
