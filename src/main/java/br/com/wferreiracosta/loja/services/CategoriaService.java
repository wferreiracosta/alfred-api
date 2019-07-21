package br.com.wferreiracosta.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wferreiracosta.loja.domain.Categoria;
import br.com.wferreiracosta.loja.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Categoria> buscar() {
		List<Categoria> obj = repo.findAll();
		return obj;
	}
	
}
