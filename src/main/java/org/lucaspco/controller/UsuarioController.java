package org.lucaspco.controller;

import org.lucaspco.dto.UsuarioDTO;
import org.lucaspco.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.salvarUsuario(usuarioDTO);
        return ResponseEntity.ok(savedUsuario);
    }

    @PutMapping("/{nome}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(
            @PathVariable String nome,
            @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setNome(nome); // Certifica que o nome no DTO corresponde ao caminho
        UsuarioDTO updatedUsuario = usuarioService.atualizarUsuario(usuarioDTO);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String nome) {
        usuarioService.deletarUsuarioPorNome(nome);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorNome(@PathVariable String nome) {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorNome(nome);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> buscarUsuarioAutenticado(Authentication authentication) {
        String username = authentication.getName();
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorNome(username);
        return ResponseEntity.ok(usuarioDTO);
    }
}
