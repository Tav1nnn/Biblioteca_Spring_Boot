package com.otavio.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.otavio.biblioteca.entities.Livro;

public class LivroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private List<AutorDTO> autores = new ArrayList<>();

	public LivroDTO () {
		
	}
	
	public LivroDTO(long id, String nome, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.nome = nome;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public LivroDTO(Livro entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.createdAt = entity.getCreatedAt();
		this.updatedAt = entity.getUpdatedAt();
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	public List<AutorDTO> getAutores() {
		return autores;
	}
}
