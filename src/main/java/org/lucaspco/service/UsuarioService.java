package org.lucaspco.service;

import org.lucaspco.dto.UsuarioDTO;
import org.lucaspco.model.Usuario;
import org.lucaspco.repository.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.buscarUsuarioPorNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(usuario.getNome(), usuario.getSenha(), new ArrayList<>());
    }

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioDAO.salvarUsuario(usuario);
        return toDTO(savedUsuario);
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorNome(usuarioDTO.getNome());
        if (usuarioExistente == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Atualiza todos os campos, exceto o nome
        usuarioExistente.setEndereco(usuarioDTO.getEndereco());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setTelefone(usuarioDTO.getTelefone());
        usuarioExistente.setNomeResponsavel(usuarioDTO.getNomeResponsavel());
        usuarioExistente.setTelefoneResponsavel(usuarioDTO.getTelefoneResponsavel());
        usuarioExistente.setCpf(usuarioDTO.getCpf());

        // Atualiza a senha apenas se for diferente
        if (!passwordEncoder.matches(usuarioDTO.getSenha(), usuarioExistente.getSenha())) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        }

        Usuario updatedUsuario = usuarioDAO.atualizarUsuario(usuarioExistente);
        return toDTO(updatedUsuario);
    }

    public void deletarUsuarioPorNome(String nome) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorNome(nome);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }
        usuarioDAO.deletarUsuario(nome); // Utiliza o nome para deletar
    }

    public UsuarioDTO buscarUsuarioPorNome(String nome) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorNome(nome);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return toDTO(usuario);
    }

    public List<UsuarioDTO> buscarTodosUsuarios() {
        return usuarioDAO.buscarTodosUsuarios()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId()); // Define o ID para atualizações
        usuario.setNome(dto.getNome());
        usuario.setEndereco(dto.getEndereco());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setNomeResponsavel(dto.getNomeResponsavel());
        usuario.setTelefoneResponsavel(dto.getTelefoneResponsavel());
        usuario.setCpf(dto.getCpf());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId()); // Mantém o ID no DTO
        dto.setNome(usuario.getNome());
        dto.setEndereco(usuario.getEndereco());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setNomeResponsavel(usuario.getNomeResponsavel());
        dto.setTelefoneResponsavel(usuario.getTelefoneResponsavel());
        dto.setCpf(usuario.getCpf());
        dto.setSenha(usuario.getSenha());
        return dto;
    }
}
