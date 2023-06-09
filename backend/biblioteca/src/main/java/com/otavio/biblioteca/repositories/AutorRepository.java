package com.otavio.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otavio.biblioteca.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
