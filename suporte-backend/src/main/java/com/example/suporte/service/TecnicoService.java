package com.example.suporte.service;

import com.example.suporte.domain.Tecnico;
import com.example.suporte.dto.TecnicoDTO;
import com.example.suporte.repository.TecnicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Page<TecnicoDTO> listar(Pageable pageable) {
        return tecnicoRepository.findAll(pageable).map(this::toDTO);
    }

    public TecnicoDTO buscarPorId(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        return toDTO(tecnico);
    }

    public TecnicoDTO criar(TecnicoDTO dto) {
        Tecnico tecnico = new Tecnico();
        aplicarDados(tecnico, dto);
        return toDTO(tecnicoRepository.save(tecnico));
    }

    public TecnicoDTO atualizar(Long id, TecnicoDTO dto) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        aplicarDados(tecnico, dto);
        return toDTO(tecnicoRepository.save(tecnico));
    }

    public void excluir(Long id) {
        if (!tecnicoRepository.existsById(id)) {
            throw new RuntimeException("Técnico não encontrado");
        }
        tecnicoRepository.deleteById(id);
    }

    private void aplicarDados(Tecnico tecnico, TecnicoDTO dto) {
        tecnico.setNome(dto.getNome());
        tecnico.setEmail(dto.getEmail());
        tecnico.setEspecialidades(dto.getEspecialidades());
    }

    private TecnicoDTO toDTO(Tecnico tecnico) {
        TecnicoDTO dto = new TecnicoDTO();
        dto.setId(tecnico.getId());
        dto.setNome(tecnico.getNome());
        dto.setEmail(tecnico.getEmail());
        dto.setEspecialidades(tecnico.getEspecialidades());
        return dto;
    }
}
