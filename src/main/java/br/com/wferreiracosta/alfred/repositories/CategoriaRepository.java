package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.alfred.models.entities.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer>{
	
}
