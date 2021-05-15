package com.wferreiracosta.alfred.service.impl;

import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.repositories.ProdutoRepository;
import com.wferreiracosta.alfred.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

  private final ProdutoRepository repository;

  @Override
  public Optional<Produto> findById(Integer id) {
    return this.repository.findById(id);
  }
  
}
