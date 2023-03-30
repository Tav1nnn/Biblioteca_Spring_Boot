package com.otavio.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.biblioteca.dto.AutorDTO;
import com.otavio.biblioteca.entities.Autor;
import com.otavio.biblioteca.repositories.AutorRepository;
import com.otavio.biblioteca.services.exceptions.DatabaseException;
import com.otavio.biblioteca.services.exceptions.ResorceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AutorService {
	@Autowired
	private AutorRepository repository;
	
	@Transactional
	public AutorDTO insert(AutorDTO dto) {
		Autor entity = new Autor(); //cria objeto livro
		entity.setNome(dto.getNome());//pega o nome do dtp
		entity.setEmail(dto.getEmail());
		entity.setNumero(dto.getNumero());
		entity.setCpf(dto.getCpf());
		
		entity = repository.save(entity);//sava no banco
		
		return new AutorDTO(entity); //retorna o objeto
		
	}

	@Transactional
	public AutorDTO update(Long id, AutorDTO dto) {
		try {
			Autor entity = repository.getOne(id);
			
			if(entity.getNome().equals(dto.getNome())) {
				throw new ResorceNotFoundException("The name is the same as the current one");
			}
			
			entity.setNome(dto.getNome());//pega o nome do dtp
			entity.setEmail(dto.getEmail());
			entity.setNumero(dto.getNumero());
			entity.setCpf(dto.getCpf());
			
			entity = repository.save(entity);
			
			
			
			return new AutorDTO(entity);
			
		}catch (EntityNotFoundException e) {
			// TODO: handle exception
			throw new ResorceNotFoundException("Id not found " + id);
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Page<AutorDTO> findAllPaged(PageRequest pageRequest) {
		Page<Autor> list = repository.findAll(pageRequest);
		
		return list.map(x -> new AutorDTO(x));
	}

	@Transactional(readOnly = true)
	public AutorDTO findById(Long id) {
		Optional<Autor> obj = repository.findById(id);
		
		Autor entity = obj.orElseThrow(()-> new ResorceNotFoundException("Entity not found"));
		
		return new AutorDTO(entity);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			throw new ResorceNotFoundException("Id not foud" + id);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new DatabaseException("Integry violation");
		}
		
	}
}
