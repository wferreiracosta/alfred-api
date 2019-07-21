package br.com.wferreiracosta.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wferreiracosta.loja.domain.Cliente;
import br.com.wferreiracosta.loja.repositories.ClienteRepository;
import br.com.wferreiracosta.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> buscar() {
		List<Cliente> obj = repo.findAll();
		return obj;
	}

}
