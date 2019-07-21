package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Integer>{
	
}
