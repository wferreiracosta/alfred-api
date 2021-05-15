package com.wferreiracosta.alfred.service.impl;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.repositories.CategoriaRepository;
import com.wferreiracosta.alfred.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

  @Autowired
  private final CategoriaRepository repository;

  @Override
  public Optional<Categoria> findById(Integer id) {
    Optional<Categoria> obj = this.repository.findById(id);
    return obj;
  }

  @Override
  public List<Categoria> findAll() {
    return this.repository.findAll();
  }

}
