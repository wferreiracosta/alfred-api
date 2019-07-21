package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Estado;

public interface EstadoRepository  extends JpaRepository<Estado, Integer>{
	
}
