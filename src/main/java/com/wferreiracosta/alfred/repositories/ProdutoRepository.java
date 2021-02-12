package com.wferreiracosta.alfred.repositories;

import com.wferreiracosta.alfred.domain.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Integer> {
  
}
