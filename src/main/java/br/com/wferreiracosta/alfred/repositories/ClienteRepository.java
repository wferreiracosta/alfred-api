package br.com.wferreiracosta.alfred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.wferreiracosta.alfred.models.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
