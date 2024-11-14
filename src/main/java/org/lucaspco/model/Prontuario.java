package org.lucaspco.model;

import javax.persistence.*;

public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String prontuario;

    private Usuario usuario;

    // Construtores
    public Prontuario() {}

    public Prontuario(String prontuario, Usuario usuario) {
        this.prontuario = prontuario;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Prontuario{" +
                "id=" + id +
                ", prontuario='" + prontuario + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
