package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wferreiracosta.alfred.domain.Pedido;

public interface PedidoRepository  extends JpaRepository<Pedido, Integer>{
	
}
