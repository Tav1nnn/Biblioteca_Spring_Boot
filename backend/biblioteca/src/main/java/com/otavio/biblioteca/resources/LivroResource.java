package com.otavio.biblioteca.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.otavio.biblioteca.dto.LivroDTO;
import com.otavio.biblioteca.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	@PostMapping
	public ResponseEntity<LivroDTO> insert(@RequestBody LivroDTO dto){
		
		dto = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Long id, @RequestBody LivroDTO dto){
		dto = service.update(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
}
