package com.otavio.biblioteca.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro implements Serializable{
	private static final long serialVersionUID = 1L;
	//Desenvolvedor | HTML, CSS, Javascript, Java | estudando Spring Boot | Padrão MVC, PWA
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String nome;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
		
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		
	}
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
	@ManyToMany
	@JoinTable(
		name = "livro_autor",
		joinColumns = @JoinColumn(name = "livro_id"),
		inverseJoinColumns = @JoinColumn(name = "autor_id")
	)
	
	Set<Autor> autores = new HashSet<>();
	
	public Livro() {
		this.createdAt = LocalDateTime.now();
	    this.updatedAt = LocalDateTime.now();
		
	}
	
	public Livro(Long id, String nome, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.nome = nome;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}
	
}
