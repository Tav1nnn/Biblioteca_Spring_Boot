package com.otavio.biblioteca.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.otavio.biblioteca.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
