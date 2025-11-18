package com.example.suporte.controller;

import com.example.suporte.dto.ticket.TicketRequestDTO;
import com.example.suporte.dto.ticket.TicketResponseDTO;
import com.example.suporte.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<Page<TicketResponseDTO>> listar(
            @RequestParam(required = false) Long criadorId,
            @NonNull Pageable pageable) {
        return ResponseEntity.ok(ticketService.listar(criadorId, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> buscarPorId(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(ticketService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> criar(@Valid @RequestBody TicketRequestDTO dto) {
        return ResponseEntity.ok(ticketService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> atualizar(@PathVariable @NonNull Long id,
                                                       @Valid @RequestBody TicketRequestDTO dto) {
        return ResponseEntity.ok(ticketService.atualizar(id, dto));
    }

    @SuppressWarnings("null")
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable @NonNull Long id) {
        ticketService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
