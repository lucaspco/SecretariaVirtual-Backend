package org.lucaspco.service;

import org.lucaspco.dto.ProntuarioDTO;
import org.lucaspco.dto.UsuarioDTO;
import org.lucaspco.model.Prontuario;
import org.lucaspco.model.Usuario;
import org.lucaspco.repository.ProntuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioDAO prontuarioDAO;

    @Autowired
    private UsuarioService usuarioService; // Assuming UsuarioService has a method to fetch Usuario by nome

    public ProntuarioDTO salvarProntuario(ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = toEntity(prontuarioDTO);
        Prontuario savedProntuario = prontuarioDAO.salvarProntuario(prontuario);
        return toDTO(savedProntuario);
    }

    public ProntuarioDTO atualizarProntuario(ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = toEntity(prontuarioDTO);
        Prontuario updatedProntuario = prontuarioDAO.atualizarProntuario(prontuario);
        return toDTO(updatedProntuario);
    }

    public void deletarProntuario(int id) {
        prontuarioDAO.deletarProntuario(id);
    }

    public ProntuarioDTO buscarProntuarioPorUsuario(String nome) {
        Prontuario prontuario = prontuarioDAO.buscarProntuarioPorUsuario(nome);
        return toDTO(prontuario);
    }

    public List<ProntuarioDTO> buscarTodosProntuarios() {
        List<Prontuario> prontuarios = prontuarioDAO.buscarTodosProntuarios();
        return prontuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Adiciona método para buscar prontuários por nome de usuário
    public List<ProntuarioDTO> buscarProntuariosPorNome(String nome) {
        List<Prontuario> prontuarios = prontuarioDAO.buscarProntuariosPorNome(nome);
        return prontuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Prontuario toEntity(ProntuarioDTO dto) {
        Prontuario prontuario = new Prontuario();
        prontuario.setId(dto.getId());
        prontuario.setProntuario(dto.getProntuario());
        return prontuario;
    }

    private ProntuarioDTO toDTO(Prontuario entity) {
        ProntuarioDTO dto = new ProntuarioDTO();
        dto.setId(entity.getId());
        dto.setProntuario(entity.getProntuario());

        // Convert and set Usuario
        if (entity.getUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(entity.getUsuario().getId());
            usuarioDTO.setNome(entity.getUsuario().getNome());
            dto.setUsuario(usuarioDTO);
        }

        return dto;
    }
}
