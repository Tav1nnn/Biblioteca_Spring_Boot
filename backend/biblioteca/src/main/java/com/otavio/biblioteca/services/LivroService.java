package com.otavio.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.biblioteca.dto.LivroDTO;
import com.otavio.biblioteca.entities.Livro;
import com.otavio.biblioteca.repositories.LivroRepository;
import com.otavio.biblioteca.services.exceptions.ResorceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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

	@Transactional
	public LivroDTO update(Long id, LivroDTO dto) {
		try {
			Livro entity = repository.getOne(id);
			
			if(entity.getNome().equals(dto.getNome())) {
				throw new ResorceNotFoundException("The name is the same as the current one");
			}
			
			entity.setNome(dto.getNome());
			entity = repository.save(entity);
			
			return new LivroDTO(entity);
			
		}catch (EntityNotFoundException e) {
			// TODO: handle exception
			throw new ResorceNotFoundException("Id not found " + id);
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Page<LivroDTO> findAllPaged(PageRequest pageRequest) {
		Page<Livro> list = repository.findAll(pageRequest);
		
		return list.map(x -> new LivroDTO(x));
	}
	
	

}
