package com.wferreiracosta.alfred.service.impl;

import java.util.Optional;

import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.repositories.ProdutoRepository;
import com.wferreiracosta.alfred.service.ProdutoService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

  private ProdutoRepository repository;

  @Override
  public Optional<Produto> findById(Integer id) {
    return this.repository.findById(id);
  }
  
}
