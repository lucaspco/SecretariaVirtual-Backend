package org.lucaspco.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id; // Adicionado para identificação interna
    private String nome;
    private String endereco;
    private String email;
    private String telefone;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private Long cpf;
    private String senha;
    private List<ProntuarioDTO> prontuarios;
    private List<PagamentoDTO> pagamentos;

    // No-argument constructor
    public UsuarioDTO() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<ProntuarioDTO> getProntuarios() {
        return prontuarios;
    }

    public void setProntuarios(List<ProntuarioDTO> prontuarios) {
        this.prontuarios = prontuarios;
    }

    public List<PagamentoDTO> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<PagamentoDTO> pagamentos) {
        this.pagamentos = pagamentos;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(endereco, that.endereco) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telefone, that.telefone) &&
                Objects.equals(nomeResponsavel, that.nomeResponsavel) &&
                Objects.equals(telefoneResponsavel, that.telefoneResponsavel) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(senha, that.senha) &&
                Objects.equals(prontuarios, that.prontuarios) &&
                Objects.equals(pagamentos, that.pagamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, endereco, email, telefone, nomeResponsavel, telefoneResponsavel, cpf, senha, prontuarios, pagamentos);
    }

    // Override toString
    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", telefoneResponsavel='" + telefoneResponsavel + '\'' +
                ", cpf=" + cpf +
                ", prontuarios=" + prontuarios +
                ", pagamentos=" + pagamentos +
                '}';
    }
}
