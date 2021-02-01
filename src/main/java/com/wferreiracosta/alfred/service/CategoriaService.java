package com.wferreiracosta.alfred.service;

import java.util.Optional;

import com.wferreiracosta.alfred.domain.Categoria;

public interface CategoriaService {

	Optional<Categoria> findById(Integer id);

}
