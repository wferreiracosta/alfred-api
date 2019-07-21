package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Endereco;

public interface EnderecoRepository  extends JpaRepository<Endereco, Integer>{
	
}
