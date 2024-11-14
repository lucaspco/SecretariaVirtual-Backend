package org.lucaspco.repository;

import org.lucaspco.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioDAO {

    private static final String INSERT_USUARIO = "INSERT INTO usuario (nome, endereco, email, telefone, nome_responsavel, telefone_responsavel, cpf, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USUARIOS = "SELECT * FROM usuario";
    private static final String SELECT_USUARIO_BY_NOME = "SELECT * FROM usuario WHERE nome = ?";
    private static final String UPDATE_USUARIO = "UPDATE usuario SET endereco = ?, email = ?, telefone = ?, nome_responsavel = ?, telefone_responsavel = ?, cpf = ?, senha = ? WHERE nome = ?";
    private static final String DELETE_USUARIO = "DELETE FROM usuario WHERE nome = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario salvarUsuario(Usuario usuario) {
        jdbcTemplate.update(INSERT_USUARIO,
                usuario.getNome(),
                usuario.getEndereco(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getNomeResponsavel(),
                usuario.getTelefoneResponsavel(),
                usuario.getCpf(),
                usuario.getSenha());
        return usuario;
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        jdbcTemplate.update(UPDATE_USUARIO,
                usuario.getEndereco(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getNomeResponsavel(),
                usuario.getTelefoneResponsavel(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getNome());
        return usuario;
    }

    public void deletarUsuario(String nome) {
        jdbcTemplate.update(DELETE_USUARIO, nome);
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USUARIO_BY_NOME, new Object[]{nome}, new UsuarioRowMapper());
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Usuario> buscarTodosUsuarios() {
        return jdbcTemplate.query(SELECT_ALL_USUARIOS, new UsuarioRowMapper());
    }

    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id")); // ID para uso interno, não exposto na API pública
            usuario.setNome(rs.getString("nome"));
            usuario.setEndereco(rs.getString("endereco"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setNomeResponsavel(rs.getString("nome_responsavel"));
            usuario.setTelefoneResponsavel(rs.getString("telefone_responsavel"));
            usuario.setCpf(rs.getLong("cpf"));
            usuario.setSenha(rs.getString("senha"));
            return usuario;
        }
    }
}
