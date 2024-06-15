package org.lucaspco.repository;

import org.lucaspco.model.Agendamento;
import org.lucaspco.model.Pagamento;
import org.lucaspco.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PagamentoDAO {

    private static final String INSERT_PAGAMENTO = "INSERT INTO pagamento (data_pagamento, valor, metodo_pagamento, usuario_nome, agendamento_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PAGAMENTOS = "SELECT * FROM pagamento";
    private static final String SELECT_PAGAMENTOS_BY_USUARIO = "SELECT * FROM pagamento WHERE usuario_nome = ?";
    private static final String UPDATE_PAGAMENTO = "UPDATE pagamento SET data_pagamento = ?, valor = ?, metodo_pagamento = ?, usuario_nome = ?, agendamento_id = ? WHERE id = ?";
    private static final String DELETE_PAGAMENTO = "DELETE FROM pagamento WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pagamento salvarPagamento(Pagamento pagamento) {
        jdbcTemplate.update(INSERT_PAGAMENTO, pagamento.getDataPagamento(), pagamento.getValor(), pagamento.getMetodoPagamento(), pagamento.getUsuario().getNome(), pagamento.getAgendamento().getId());
        return pagamento;
    }

    public Pagamento atualizarPagamento(Pagamento pagamento) {
        jdbcTemplate.update(UPDATE_PAGAMENTO, pagamento.getDataPagamento(), pagamento.getValor(), pagamento.getMetodoPagamento(), pagamento.getUsuario().getNome(), pagamento.getAgendamento().getId(), pagamento.getId());
        return pagamento;
    }

    public void deletarPagamento(Integer pagamentoId) {
        jdbcTemplate.update(DELETE_PAGAMENTO, pagamentoId);
    }

    public List<Pagamento> buscarPagamentosPorUsuario(String nomeUsuario) {
        return jdbcTemplate.query(SELECT_PAGAMENTOS_BY_USUARIO, new Object[]{nomeUsuario}, new PagamentoRowMapper());
    }

    public List<Pagamento> buscarTodosPagamentos() {
        return jdbcTemplate.query(SELECT_ALL_PAGAMENTOS, new PagamentoRowMapper());
    }

    private static class PagamentoRowMapper implements RowMapper<Pagamento> {
        @Override
        public Pagamento mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pagamento pagamento = new Pagamento();
            pagamento.setId(rs.getInt("id"));
            pagamento.setDataPagamento(rs.getTimestamp("data_pagamento").toLocalDateTime());
            pagamento.setValor(rs.getDouble("valor"));
            pagamento.setMetodoPagamento(rs.getString("metodo_pagamento"));

            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("usuario_nome"));
            pagamento.setUsuario(usuario);

            Agendamento agendamento = new Agendamento();
            agendamento.setId(rs.getInt("agendamento_id"));
            pagamento.setAgendamento(agendamento);

            return pagamento;
        }
    }
}
