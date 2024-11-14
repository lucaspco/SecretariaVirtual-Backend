package org.lucaspco.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String endereco;

    private String email;

    private String telefone;

    private String nomeResponsavel;

    private String telefoneResponsavel;

    private Long cpf;

    private String senha;

    private List<Prontuario> prontuarios = new ArrayList<>();

    private List<Pagamento> pagamentos = new ArrayList<>();

    // Getters e Setters
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
        if (email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
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
        if (cpf != null && cpf.toString().matches("\\d{11}")) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("Invalid CPF format");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Prontuario> getProntuarios() {
        return prontuarios;
    }

    public void setProntuarios(List<Prontuario> prontuarios) {
        this.prontuarios = prontuarios;
    }

    public void addProntuario(Prontuario prontuario) {
        prontuarios.add(prontuario);
        prontuario.setUsuario(this);
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public void addPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
        pagamento.setUsuario(this);
    }

    @Override
    public String toString() {
        return "Usuario{" +
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
