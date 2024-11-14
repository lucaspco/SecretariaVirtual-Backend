package org.lucaspco.dto;

import java.io.Serializable;

public class ProntuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String prontuario;
    private UsuarioDTO usuario;

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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
