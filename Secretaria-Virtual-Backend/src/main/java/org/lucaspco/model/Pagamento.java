package org.lucaspco.model;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataPagamento;

    private Double valor;

    private String metodoPagamento;

    private Usuario usuario;

    private Agendamento agendamento;

    // Construtores
    public Pagamento() {
    }

    public Pagamento(LocalDateTime dataPagamento, Double valor, String metodoPagamento, Usuario usuario, Agendamento agendamento) {
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.usuario = usuario;
        this.agendamento = agendamento;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", dataPagamento=" + dataPagamento +
                ", valor=" + valor +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", usuario=" + usuario +
                ", agendamento=" + agendamento +
                '}';
    }
}
