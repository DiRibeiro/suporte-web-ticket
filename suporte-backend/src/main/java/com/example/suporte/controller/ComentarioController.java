package com.example.suporte.controller;

import com.example.suporte.dto.ComentarioDTO;
import com.example.suporte.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<Page<ComentarioDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(comentarioService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> criar(@Valid @RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> atualizar(@PathVariable Long id,
                                                   @Valid @RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comentarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
