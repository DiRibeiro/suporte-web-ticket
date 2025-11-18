package com.example.suporte.dto.ticket;

import com.example.suporte.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketRequestDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer impacto;

    @NotNull
    private Integer urgencia;

    @NotNull
    private Integer prioridade;

    @NotNull
    private Status status;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long criadorId;

    private Long tecnicoAtribuidoId;

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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Long getCriadorId() {
		return criadorId;
	}

	public void setCriadorId(Long criadorId) {
		this.criadorId = criadorId;
	}

	public Long getTecnicoAtribuidoId() {
		return tecnicoAtribuidoId;
	}

	public void setTecnicoAtribuidoId(Long tecnicoAtribuidoId) {
		this.tecnicoAtribuidoId = tecnicoAtribuidoId;
	}
}
