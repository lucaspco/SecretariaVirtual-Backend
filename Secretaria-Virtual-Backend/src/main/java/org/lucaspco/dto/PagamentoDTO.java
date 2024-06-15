package org.lucaspco.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private LocalDateTime dataPagamento;
    private Double valor;
    private String metodoPagamento;
    private UsuarioDTO usuario;
    private AgendamentoDTO agendamento;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public AgendamentoDTO getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AgendamentoDTO agendamento) {
        this.agendamento = agendamento;
    }
}
