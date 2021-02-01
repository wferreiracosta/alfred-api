package com.wferreiracosta.alfred.service.impl;

import java.util.Optional;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.repositories.CategoriaRepository;
import com.wferreiracosta.alfred.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  @Override
  public Optional<Categoria> findById(Integer id) {
    Optional<Categoria> obj = this.repository.findById(id);
    return obj;
  }

}
