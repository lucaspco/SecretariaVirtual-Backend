package org.lucaspco.controller;

import org.lucaspco.dto.ProntuarioDTO;
import org.lucaspco.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<ProntuarioDTO> criarProntuario(@RequestBody ProntuarioDTO prontuarioDTO) {
        ProntuarioDTO savedProntuario = prontuarioService.salvarProntuario(prontuarioDTO);
        return new ResponseEntity<>(savedProntuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioDTO> atualizarProntuario(
            @PathVariable Integer id,
            @RequestBody ProntuarioDTO prontuarioDTO) {
        prontuarioDTO.setId(id); // Certifica que o ID no DTO corresponde ao caminho
        ProntuarioDTO updatedProntuario = prontuarioService.atualizarProntuario(prontuarioDTO);
        return ResponseEntity.ok(updatedProntuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProntuario(@PathVariable Integer id) {
        prontuarioService.deletarProntuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProntuarioDTO>> listarTodosProntuarios() {
        List<ProntuarioDTO> prontuarios = prontuarioService.buscarTodosProntuarios();
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/usuario/{nome}")
    public ResponseEntity<List<ProntuarioDTO>> buscarProntuariosPorNome(@PathVariable String nome) {
        List<ProntuarioDTO> prontuarios = prontuarioService.buscarProntuariosPorNome(nome);
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/me")
    public ResponseEntity<List<ProntuarioDTO>> buscarProntuariosProprios(Authentication authentication) {
        String username = authentication.getName();
        List<ProntuarioDTO> prontuarios = prontuarioService.buscarProntuariosPorNome(username);
        return ResponseEntity.ok(prontuarios);
    }
}
