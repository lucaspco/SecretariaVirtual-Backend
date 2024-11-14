package org.lucaspco.controller;

import org.lucaspco.dto.AgendamentoDTO;
import org.lucaspco.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoDTO> salvarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO, Authentication authentication) {
        AgendamentoDTO savedAgendamento = agendamentoService.salvarAgendamento(agendamentoDTO, authentication);
        return ResponseEntity.ok(savedAgendamento);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> buscarTodosAgendamentos() {
        List<AgendamentoDTO> agendamentos = agendamentoService.buscarTodosAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/usuario/{nome}")
    public ResponseEntity<List<AgendamentoDTO>> buscarAgendamentosPorUsuario(@PathVariable String nome) {
        List<AgendamentoDTO> agendamentos = agendamentoService.buscarAgendamentosPorUsuario(nome);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AgendamentoDTO>> buscarMeusAgendamentos(Authentication authentication) {
        String username = authentication.getName();
        List<AgendamentoDTO> meusAgendamentos = agendamentoService.buscarAgendamentosPorUsuario(username);
        return ResponseEntity.ok(meusAgendamentos);
    }
}
