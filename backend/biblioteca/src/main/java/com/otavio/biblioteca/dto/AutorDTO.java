package com.otavio.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.otavio.biblioteca.entities.Autor;

public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private Long id;
	private String nome;
	private String email;
	private String numero;
	private int cpf;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public AutorDTO() {
		super();
	}
	
	public AutorDTO(Long id, String nome, String email, String numero, int cpf, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.numero = numero;
		this.cpf = cpf;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public AutorDTO(Autor entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.numero = entity.getNumero();
		this.cpf = entity.getCpf();
		this.createdAt = entity.getCreatedAt();
		this.updatedAt = entity.getUpdatedAt();
	}
	
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
