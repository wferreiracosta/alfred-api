package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer>{
	
}
