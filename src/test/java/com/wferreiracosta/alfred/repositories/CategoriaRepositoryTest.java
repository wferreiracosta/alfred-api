package com.wferreiracosta.alfred.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.domain.CategoriaTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class CategoriaRepositoryTest {
    
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private CategoriaRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando a categoria existir")
    public void retornarVerdadeiroQuandoExistirCategoria(){
        Categoria categoria = CategoriaTest.criarCategoriaComIdAutomatico();
        Categoria categoriaSalva = this.entityManager.persist(categoria);
        boolean existeCategoria = this.repository.existsById(categoriaSalva.getId());
        assertThat(existeCategoria).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando a categoria não existir")
    public void retornarFalsoQuandoNaoExistirCategoria(){
        Integer id = 1;
        boolean existeCategoria = this.repository.existsById(id);
        assertThat(existeCategoria).isFalse();
    }

    @Test
    @DisplayName("Deve obter uma categoria por id")
    public void deveRetornaCategoria(){
        Categoria categoria = CategoriaTest.criarCategoriaComIdAutomatico();
        Categoria categoriaSalva = this.entityManager.persist(categoria);
        Optional<Categoria> categoriaRetornada = this.repository.findById(categoriaSalva.getId());
        assertThat(categoriaRetornada.isPresent()).isTrue();
    }
}
