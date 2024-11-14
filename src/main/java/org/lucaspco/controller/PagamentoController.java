package org.lucaspco.controller;

import org.lucaspco.dto.PagamentoDTO;
import org.lucaspco.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoDTO> salvarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO savedPagamento = pagamentoService.salvarPagamento(pagamentoDTO);
        return ResponseEntity.ok(savedPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizarPagamento(
            @PathVariable Integer id,
            @RequestBody PagamentoDTO pagamentoDTO) {
        pagamentoDTO.setId(id); // Certifica que o ID no DTO corresponde ao caminho
        PagamentoDTO updatedPagamento = pagamentoService.atualizarPagamento(pagamentoDTO);
        return ResponseEntity.ok(updatedPagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Integer id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{nome}")
    public ResponseEntity<List<PagamentoDTO>> buscarPagamentosPorUsuario(@PathVariable String nome) {
        List<PagamentoDTO> pagamentos = pagamentoService.buscarPagamentosPorUsuario(nome);
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> buscarTodosPagamentos() {
        List<PagamentoDTO> pagamentos = pagamentoService.buscarTodosPagamentos();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/me")
    public ResponseEntity<List<PagamentoDTO>> buscarPagamentosDoUsuarioAutenticado(Authentication authentication) {
        String username = authentication.getName();
        List<PagamentoDTO> meusPagamentos = pagamentoService.buscarPagamentosPorUsuario(username);
        return ResponseEntity.ok(meusPagamentos);
    }
}
