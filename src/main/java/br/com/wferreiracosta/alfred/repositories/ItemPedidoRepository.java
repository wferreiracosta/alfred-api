package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.alfred.domain.ItemPedido;

public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, Integer>{
	
}