package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.Pedido;

public interface PedidoRepository  extends JpaRepository<Pedido, Integer>{
	
}
