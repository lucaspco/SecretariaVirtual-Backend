package org.lucaspco.service;

import org.lucaspco.dto.AgendamentoDTO;
import org.lucaspco.dto.PagamentoDTO;
import org.lucaspco.dto.UsuarioDTO;
import org.lucaspco.model.Agendamento;
import org.lucaspco.model.Pagamento;
import org.lucaspco.model.Usuario;
import org.lucaspco.repository.PagamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoDAO pagamentoDAO;

    @Autowired
    private UsuarioService usuarioService; // Assuming UsuarioService has a method to fetch Usuario by nome

    @Autowired
    private AgendamentoService agendamentoService; // Assuming AgendamentoService has a method to fetch Agendamento by id

    public PagamentoDTO salvarPagamento(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = toEntity(pagamentoDTO);
        Pagamento savedPagamento = pagamentoDAO.salvarPagamento(pagamento);
        return toDTO(savedPagamento);
    }

    public PagamentoDTO atualizarPagamento(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = toEntity(pagamentoDTO);
        Pagamento updatedPagamento = pagamentoDAO.atualizarPagamento(pagamento);
        return toDTO(updatedPagamento);
    }

    public void deletarPagamento(Integer pagamentoId) {
        pagamentoDAO.deletarPagamento(pagamentoId);
    }

    public List<PagamentoDTO> buscarPagamentosPorUsuario(String nomeUsuario) {
        List<Pagamento> pagamentos = pagamentoDAO.buscarPagamentosPorUsuario(nomeUsuario);
        return pagamentos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PagamentoDTO> buscarTodosPagamentos() {
        List<Pagamento> pagamentos = pagamentoDAO.buscarTodosPagamentos();
        return pagamentos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Pagamento toEntity(PagamentoDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(dto.getId());
        pagamento.setDataPagamento(dto.getDataPagamento());
        pagamento.setValor(dto.getValor());
        pagamento.setMetodoPagamento(dto.getMetodoPagamento());
        return pagamento;
    }

    private PagamentoDTO toDTO(Pagamento pagamento) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(pagamento.getId());
        dto.setDataPagamento(pagamento.getDataPagamento());
        dto.setValor(pagamento.getValor());
        dto.setMetodoPagamento(pagamento.getMetodoPagamento());

        // Convert and set Usuario
        if (pagamento.getUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(pagamento.getUsuario().getId());
            usuarioDTO.setNome(pagamento.getUsuario().getNome());
            usuarioDTO.setEmail(pagamento.getUsuario().getEmail());
            dto.setUsuario(usuarioDTO);
        }

        // Convert and set Agendamento
        if (pagamento.getAgendamento() != null) {
            AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
            agendamentoDTO.setId(pagamento.getAgendamento().getId());
            agendamentoDTO.setDataHora(pagamento.getAgendamento().getDataHora());
            agendamentoDTO.setDescricao(pagamento.getAgendamento().getDescricao());
            dto.setAgendamento(agendamentoDTO);
        }

        return dto;
    }
}
