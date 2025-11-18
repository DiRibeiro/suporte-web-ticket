package com.example.suporte.controller;

import com.example.suporte.dto.TecnicoDTO;
import com.example.suporte.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public ResponseEntity<Page<TecnicoDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(tecnicoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> criar(@Valid @RequestBody TecnicoDTO dto) {
        return ResponseEntity.ok(tecnicoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> atualizar(@PathVariable Long id,
                                                @Valid @RequestBody TecnicoDTO dto) {
        return ResponseEntity.ok(tecnicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tecnicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
