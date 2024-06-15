package org.lucaspco.service;

import org.lucaspco.dto.AgendamentoDTO;
import org.lucaspco.dto.PagamentoDTO;
import org.lucaspco.dto.UsuarioDTO;
import org.lucaspco.model.Agendamento;
import org.lucaspco.model.Pagamento;
import org.lucaspco.model.Usuario;
import org.lucaspco.repository.AgendamentoDAO;
import org.lucaspco.repository.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoDAO agendamentoDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    public AgendamentoDTO salvarAgendamento(AgendamentoDTO agendamentoDTO, Authentication authentication) {
        Agendamento agendamento = toEntity(agendamentoDTO);

        // Obtém o nome do usuário logado
        String username = authentication.getName();

        // Busca o usuário logado
        Usuario usuario = usuarioDAO.buscarUsuarioPorNome(username);

        agendamento.setUsuario(usuario);
        Agendamento savedAgendamento = agendamentoDAO.salvarAgendamento(agendamento);
        return toDTO(savedAgendamento);
    }

    public AgendamentoDTO atualizarAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = toEntity(agendamentoDTO);
        Agendamento updatedAgendamento = agendamentoDAO.atualizarAgendamento(agendamento);
        return toDTO(updatedAgendamento);
    }

    public void deletarAgendamento(int agendamentoId) {
        agendamentoDAO.deletarAgendamento(agendamentoId);
    }

    public AgendamentoDTO buscarAgendamentoPorId(int agendamentoId) {
        Agendamento agendamento = agendamentoDAO.buscarAgendamentoPorId(agendamentoId);
        return toDTO(agendamento);
    }

    public List<AgendamentoDTO> buscarAgendamentosPorUsuario(String usuarioNome) {
        List<Agendamento> agendamentos = agendamentoDAO.buscarAgendamentosPorUsuario(usuarioNome);
        return agendamentos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AgendamentoDTO> buscarTodosAgendamentos() {
        return agendamentoDAO.buscarTodosAgendamentos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private Agendamento toEntity(AgendamentoDTO dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(dto.getId());
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setDescricao(dto.getDescricao());

        // Convert and set Pagamentos
        if (dto.getPagamentos() != null) {
            List<Pagamento> pagamentos = dto.getPagamentos().stream()
                    .map(pagamentoDTO -> {
                        Pagamento pagamento = new Pagamento();
                        pagamento.setId(pagamentoDTO.getId());
                        pagamento.setValor(pagamentoDTO.getValor());
                        pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
                        return pagamento;
                    })
                    .collect(Collectors.toList());
            agendamento.setPagamentos(pagamentos);
        }

        return agendamento;
    }

    private AgendamentoDTO toDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(agendamento.getId());
        dto.setDataHora(agendamento.getDataHora());
        dto.setDescricao(agendamento.getDescricao());

        // Convert and set Pagamentos
        if (agendamento.getPagamentos() != null) {
            List<PagamentoDTO> pagamentosDTO = agendamento.getPagamentos().stream()
                    .map(pagamento -> {
                        PagamentoDTO pagamentoDTO = new PagamentoDTO();
                        pagamentoDTO.setId(pagamento.getId());
                        pagamentoDTO.setValor(pagamento.getValor());
                        pagamentoDTO.setDataPagamento(pagamento.getDataPagamento());
                        return pagamentoDTO;
                    })
                    .collect(Collectors.toList());
            dto.setPagamentos(pagamentosDTO);
        }

        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
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
        dto.setId(usuario.getId());
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
