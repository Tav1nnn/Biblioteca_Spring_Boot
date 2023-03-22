package com.otavio.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.biblioteca.dto.LivroDTO;
import com.otavio.biblioteca.entities.Livro;
import com.otavio.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	@Transactional
	public LivroDTO insert(LivroDTO dto) {
		Livro entity = new Livro(); //cria objeto livro
		entity.setNome(dto.getNome());//pega o nome do dtp
		
		entity = repository.save(entity);//sava no banco
		
		return new LivroDTO(entity); //retorna o objeto
		
	}

}
