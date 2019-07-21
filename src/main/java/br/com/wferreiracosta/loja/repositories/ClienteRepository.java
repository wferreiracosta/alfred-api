package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{
	
}
