package com.example.suporte.service;

import com.example.suporte.domain.Comentario;
import com.example.suporte.domain.Ticket;
import com.example.suporte.dto.ComentarioDTO;
import com.example.suporte.repository.ComentarioRepository;
import com.example.suporte.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final TicketRepository ticketRepository;

    public ComentarioService(ComentarioRepository comentarioRepository,
                             TicketRepository ticketRepository) {
        this.comentarioRepository = comentarioRepository;
        this.ticketRepository = ticketRepository;
    }

    public Page<ComentarioDTO> listar(Pageable pageable) {
        return comentarioRepository.findAll(pageable).map(this::toDTO);
    }

    public ComentarioDTO buscarPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
        return toDTO(comentario);
    }

    public ComentarioDTO criar(ComentarioDTO dto) {
        Comentario comentario = new Comentario();
        aplicarDados(comentario, dto);
        return toDTO(comentarioRepository.save(comentario));
    }

    public ComentarioDTO atualizar(Long id, ComentarioDTO dto) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
        aplicarDados(comentario, dto);
        return toDTO(comentarioRepository.save(comentario));
    }

    public void excluir(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentário não encontrado");
        }
        comentarioRepository.deleteById(id);
    }

    private void aplicarDados(Comentario comentario, ComentarioDTO dto) {
        comentario.setMensagem(dto.getMensagem());
        Ticket ticket = ticketRepository.findById(dto.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        comentario.setTicket(ticket);
    }

    private ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setMensagem(comentario.getMensagem());
        dto.setCriadoEm(comentario.getCriadoEm());
        if (comentario.getTicket() != null) {
            dto.setTicketId(comentario.getTicket().getId());
        }
        return dto;
    }
}
