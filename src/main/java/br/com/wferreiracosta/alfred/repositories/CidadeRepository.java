package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.alfred.domain.Cidade;

public interface CidadeRepository  extends JpaRepository<Cidade, Integer>{
	
}
