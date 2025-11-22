package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.alfred.models.entities.Pagamento;

public interface PagamentoRepository  extends JpaRepository<Pagamento, Integer>{
	
}
