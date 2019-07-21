package br.com.wferreiracosta.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.loja.domain.ItemPedido;

public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, Integer>{
	
}