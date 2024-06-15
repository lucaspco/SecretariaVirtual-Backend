package org.lucaspco.repository;

import org.lucaspco.model.Prontuario;
import org.lucaspco.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProntuarioDAO {

    private static final String INSERT_PRONTUARIO = "INSERT INTO prontuario (prontuario, usuario_nome) VALUES (?, ?)";
    private static final String SELECT_ALL_PRONTUARIOS = "SELECT * FROM prontuario";
    private static final String SELECT_PRONTUARIO_BY_USUARIO = "SELECT * FROM prontuario WHERE usuario_nome = ?";
    private static final String UPDATE_PRONTUARIO = "UPDATE prontuario SET prontuario = ?, usuario_nome = ? WHERE id = ?";
    private static final String DELETE_PRONTUARIO = "DELETE FROM prontuario WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Prontuario salvarProntuario(Prontuario prontuario) {
        jdbcTemplate.update(INSERT_PRONTUARIO, prontuario.getProntuario(), prontuario.getUsuario().getNome());
        return prontuario;
    }

    public Prontuario atualizarProntuario(Prontuario prontuario) {
        jdbcTemplate.update(UPDATE_PRONTUARIO, prontuario.getProntuario(), prontuario.getUsuario().getNome(), prontuario.getId());
        return prontuario;
    }

    public void deletarProntuario(int prontuarioId) {
        jdbcTemplate.update(DELETE_PRONTUARIO, prontuarioId);
    }

    public Prontuario buscarProntuarioPorUsuario(String nomeUsuario) {
        return jdbcTemplate.queryForObject(SELECT_PRONTUARIO_BY_USUARIO, new Object[]{nomeUsuario}, new ProntuarioRowMapper());
    }

    public List<Prontuario> buscarTodosProntuarios() {
        return jdbcTemplate.query(SELECT_ALL_PRONTUARIOS, new ProntuarioRowMapper());
    }

    // Adiciona método para buscar todos os prontuários por nome de usuário
    public List<Prontuario> buscarProntuariosPorNome(String nomeUsuario) {
        return jdbcTemplate.query(SELECT_PRONTUARIO_BY_USUARIO, new Object[]{nomeUsuario}, new ProntuarioRowMapper());
    }

    private static class ProntuarioRowMapper implements RowMapper<Prontuario> {
        @Override
        public Prontuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Prontuario prontuario = new Prontuario();
            prontuario.setId(rs.getInt("id"));
            prontuario.setProntuario(rs.getString("prontuario"));

            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("usuario_nome"));
            prontuario.setUsuario(usuario);

            return prontuario;
        }
    }
}
