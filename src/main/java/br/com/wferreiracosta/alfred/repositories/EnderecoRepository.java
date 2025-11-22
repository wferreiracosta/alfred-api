package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.alfred.domain.Endereco;

public interface EnderecoRepository  extends JpaRepository<Endereco, Integer>{
	
}
