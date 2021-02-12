package com.wferreiracosta.alfred.service;

import java.util.Optional;

import com.wferreiracosta.alfred.domain.Produto;

public interface ProdutoService {

  Optional<Produto> findById(Integer id);

}
