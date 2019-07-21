package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Cidade;

public interface CidadeRepository  extends JpaRepository<Cidade, Integer>{
	
}
