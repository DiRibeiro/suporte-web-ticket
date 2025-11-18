package com.example.suporte.service;

import com.example.suporte.domain.Categoria;
import com.example.suporte.domain.Tecnico;
import com.example.suporte.domain.Ticket;
import com.example.suporte.domain.Usuario;
import com.example.suporte.dto.ticket.TicketRequestDTO;
import com.example.suporte.dto.ticket.TicketResponseDTO;
import com.example.suporte.repository.CategoriaRepository;
import com.example.suporte.repository.TecnicoRepository;
import com.example.suporte.repository.TicketRepository;
import com.example.suporte.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final TecnicoRepository tecnicoRepository;
    private final CategoriaRepository categoriaRepository;

    public TicketService(TicketRepository ticketRepository,
                         UsuarioRepository usuarioRepository,
                         TecnicoRepository tecnicoRepository,
                         CategoriaRepository categoriaRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Page<TicketResponseDTO> listar(Long criadorId, @NonNull Pageable pageable) {
        Page<Ticket> page;
        if (criadorId != null) {
            page = ticketRepository.findByCriadorId(criadorId, pageable);
        } else {
            page = ticketRepository.findAll(pageable);
        }
        return page.map(this::toResponseDTO);
    }

    public TicketResponseDTO buscarPorId(@NonNull Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        return toResponseDTO(ticket);
    }

    public TicketResponseDTO criar(TicketRequestDTO dto) {
        Ticket ticket = new Ticket();
        aplicarDados(ticket, dto);
        Ticket salvo = ticketRepository.save(ticket);
        return toResponseDTO(salvo);
    }

    @SuppressWarnings("null")
	public TicketResponseDTO atualizar(@Nullable Long id, TicketRequestDTO dto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        aplicarDados(ticket, dto);
        @SuppressWarnings("null")
		Ticket salvo = ticketRepository.save(ticket);
        return toResponseDTO(salvo);
    }

    public void excluir(@NonNull Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket não encontrado");
        }
        ticketRepository.deleteById(id);
    }

    private void aplicarDados(Ticket ticket, TicketRequestDTO dto) {
        ticket.setTitulo(dto.getTitulo());
        ticket.setDescricao(dto.getDescricao());
        ticket.setImpacto(dto.getImpacto());
        ticket.setUrgencia(dto.getUrgencia());
        ticket.setPrioridade(dto.getPrioridade());
        ticket.setStatus(dto.getStatus());

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        ticket.setCategoria(categoria);

        Usuario criador = usuarioRepository.findById(dto.getCriadorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ticket.setCriador(criador);

        if (dto.getTecnicoAtribuidoId() != null) {
            Tecnico tecnico = tecnicoRepository.findById(dto.getTecnicoAtribuidoId())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
            ticket.setTecnicoAtribuido(tecnico);
        } else {
            ticket.setTecnicoAtribuido(null);
        }
    }

    private TicketResponseDTO toResponseDTO(Ticket ticket) {
        TicketResponseDTO dto = new TicketResponseDTO();
        dto.setId(ticket.getId());
        dto.setTitulo(ticket.getTitulo());
        dto.setDescricao(ticket.getDescricao());
        dto.setImpacto(ticket.getImpacto());
        dto.setUrgencia(ticket.getUrgencia());
        dto.setPrioridade(ticket.getPrioridade());
        dto.setStatus(ticket.getStatus());
        dto.setCriadoEm(ticket.getCriadoEm());
        dto.setAtualizadoEm(ticket.getAtualizadoEm());

        if (ticket.getCategoria() != null) {
            dto.setCategoriaId(ticket.getCategoria().getId());
            dto.setCategoriaNome(ticket.getCategoria().getNome());
        }

        if (ticket.getCriador() != null) {
            dto.setCriadorId(ticket.getCriador().getId());
            dto.setCriadorNome(ticket.getCriador().getNome());
        }

        if (ticket.getTecnicoAtribuido() != null) {
            dto.setTecnicoAtribuidoId(ticket.getTecnicoAtribuido().getId());
            dto.setTecnicoAtribuidoNome(ticket.getTecnicoAtribuido().getNome());
        }

        return dto;
    }
}
