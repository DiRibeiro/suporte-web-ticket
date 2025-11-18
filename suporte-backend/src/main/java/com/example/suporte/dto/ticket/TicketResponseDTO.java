package com.example.suporte.dto.ticket;

import com.example.suporte.domain.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Integer impacto;
    private Integer urgencia;
    private Integer prioridade;
    private Status status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    private Long categoriaId;
    private String categoriaNome;

    private Long criadorId;
    private String criadorNome;

    private Long tecnicoAtribuidoId;
    private String tecnicoAtribuidoNome;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getImpacto() {
		return impacto;
	}
	public void setImpacto(Integer impacto) {
		this.impacto = impacto;
	}
	public Integer getUrgencia() {
		return urgencia;
	}
	public void setUrgencia(Integer urgencia) {
		this.urgencia = urgencia;
	}
	public Integer getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}
	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}
	public void setAtualizadoEm(LocalDateTime atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getCategoriaNome() {
		return categoriaNome;
	}
	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}
	public Long getCriadorId() {
		return criadorId;
	}
	public void setCriadorId(Long criadorId) {
		this.criadorId = criadorId;
	}
	public String getCriadorNome() {
		return criadorNome;
	}
	public void setCriadorNome(String criadorNome) {
		this.criadorNome = criadorNome;
	}
	public Long getTecnicoAtribuidoId() {
		return tecnicoAtribuidoId;
	}
	public void setTecnicoAtribuidoId(Long tecnicoAtribuidoId) {
		this.tecnicoAtribuidoId = tecnicoAtribuidoId;
	}
	public String getTecnicoAtribuidoNome() {
		return tecnicoAtribuidoNome;
	}
	public void setTecnicoAtribuidoNome(String tecnicoAtribuidoNome) {
		this.tecnicoAtribuidoNome = tecnicoAtribuidoNome;
	}
}
