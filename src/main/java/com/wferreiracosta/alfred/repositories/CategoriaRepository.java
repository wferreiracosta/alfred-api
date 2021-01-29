package com.wferreiracosta.alfred.repositories;

import com.wferreiracosta.alfred.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {
    
}
