package com.otavio.biblioteca.dto;

import java.io.Serializable;

import com.otavio.biblioteca.entities.Livro;

public class LivroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	
	public LivroDTO () {
		
	}

	public LivroDTO(Livro entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

	public LivroDTO(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	
}
