package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Pagamento;

public interface PagamentoRepository  extends JpaRepository<Pagamento, Integer>{
	
}
