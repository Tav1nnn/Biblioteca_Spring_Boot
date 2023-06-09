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
import com.otavio.biblioteca.dto.LivroDTO;
import com.otavio.biblioteca.entities.Autor;
import com.otavio.biblioteca.entities.Livro;
import com.otavio.biblioteca.repositories.AutorRepository;
import com.otavio.biblioteca.repositories.LivroRepository;
import com.otavio.biblioteca.services.exceptions.DatabaseException;
import com.otavio.biblioteca.services.exceptions.ResorceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	@Autowired
	private AutorRepository autorRepository;
	
	@Transactional
	public LivroDTO insert(LivroDTO dto) {
		Livro entity = new Livro(); //cria objeto livro
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new LivroDTO(entity,  entity.getAutores());
		
	}

	private void copyDtoToEntity(LivroDTO dto, Livro entity) {
		// TODO Auto-generated method stub
		entity.setNome(dto.getNome());//pega o nome do dtp
		entity.getAutores().clear();
		
		for(AutorDTO autordto : dto.getAutores()) {
			Autor autor = autorRepository.getOne(autordto.getId());
			entity.getAutores().add(autor);
		}
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
			
			System.out.println("teste" + entity.getUpdatedAt());
			
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

	@Transactional(readOnly = true)
	public LivroDTO findById(Long id) {
		Optional<Livro> obj = repository.findById(id);
		
		Livro entity = obj.orElseThrow(()-> new ResorceNotFoundException("Entity not found"));
		
		return new LivroDTO(entity, entity.getAutores());
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
