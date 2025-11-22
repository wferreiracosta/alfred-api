package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.alfred.models.entities.Estado;

public interface EstadoRepository  extends JpaRepository<Estado, Integer>{
	
}
