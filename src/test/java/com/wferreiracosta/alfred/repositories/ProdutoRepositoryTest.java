package com.wferreiracosta.alfred.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.domain.ProdutoTest;

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
public class ProdutoRepositoryTest {
  
  @Autowired
  TestEntityManager entityManager;

  @Autowired
  ProdutoRepository repository;

  @Test
  @DisplayName("Deve retornar verdadeira quando o Produto existir")
  public void deveRetornarverdadeiroQuandoProdutoExistir(){
    Produto produto = ProdutoTest.criarProdutoComIdAutomatico();
    Produto produtoSalvo = this.entityManager.persist(produto);
    boolean existeProduto = this.repository.existsById(produtoSalvo.getId());
    assertThat(existeProduto).isTrue();
  }
}
