package org.lucaspco.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private LocalDateTime dataHora;
    private String descricao;
    private String usuarioNome; // Usar o nome do usuário
    private List<PagamentoDTO> pagamentos;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public List<PagamentoDTO> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<PagamentoDTO> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
