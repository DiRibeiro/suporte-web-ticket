package com.example.suporte.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @ElementCollection
    @CollectionTable(name = "tecnico_especialidades",
            joinColumns = @JoinColumn(name = "tecnico_id"))
    @Column(name = "especialidade")
    private List<String> especialidades = new ArrayList<>();

    @OneToMany(mappedBy = "tecnicoAtribuido")
    private List<Ticket> ticketsAtribuidos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Ticket> getTicketsAtribuidos() {
		return ticketsAtribuidos;
	}

	public void setTicketsAtribuidos(List<Ticket> ticketsAtribuidos) {
		this.ticketsAtribuidos = ticketsAtribuidos;
	}
}
