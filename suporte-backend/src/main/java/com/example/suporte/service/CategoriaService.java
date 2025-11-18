package com.example.suporte.service;

import com.example.suporte.domain.Categoria;
import com.example.suporte.dto.CategoriaDTO;
import com.example.suporte.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Page<CategoriaDTO> listar(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(this::toDTO);
    }

    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return toDTO(categoria);
    }

    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        aplicarDados(categoria, dto);
        return toDTO(categoriaRepository.save(categoria));
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        aplicarDados(categoria, dto);
        return toDTO(categoriaRepository.save(categoria));
    }

    public void excluir(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    private void aplicarDados(Categoria categoria, CategoriaDTO dto) {
        categoria.setNome(dto.getNome());
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        return dto;
    }
}
