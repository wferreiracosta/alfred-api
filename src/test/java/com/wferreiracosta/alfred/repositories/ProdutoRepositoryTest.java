package com.wferreiracosta.alfred.repositories;

import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.domain.ProdutoTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoRepositoryTest extends RepositoryTest {
  
  @Autowired
  TestEntityManager entityManager;

  @Autowired
  ProdutoRepository repository;

  @Test
  @DisplayName("Deve retornar verdadeira quando o Produto existir")
  public void deveRetornarVerdadeiroQuandoProdutoExistir(){
    Produto produto = ProdutoTest.criarProdutoComIdAutomatico();
    Produto produtoSalvo = this.entityManager.persist(produto);
    boolean existeProduto = this.repository.existsById(produtoSalvo.getId());
    assertThat(existeProduto).isTrue();
  }

  @Test
  @DisplayName("Deve retornar falso quando o Produto não existir")
  public void deveRetornarFalsoQuandoProdutoNaoExistir(){
    Integer id = 1;
    boolean existeProduto = this.repository.existsById(id);
    assertThat(existeProduto).isFalse();
  }

  @Test
  @DisplayName("Deve retornar o produto")
  public void deveRetornarProduto(){
    Produto produto = ProdutoTest.criarProdutoComIdAutomatico();
    Produto produtoSalvo = this.entityManager.persist(produto);
    Optional<Produto> produtoRetornado = this.repository.findById(produtoSalvo.getId());

    assertThat(produtoRetornado.get().getId()).isNotNull();
    assertThat(produtoRetornado.get().getId()).isEqualTo(produtoSalvo.getId());
    assertThat(produtoRetornado.get().getNome()).isEqualTo(produtoSalvo.getNome());
    assertThat(produtoRetornado.get().getPreco()).isEqualTo(produtoSalvo.getPreco());
  }

  @Test
  @DisplayName("Deve apagar um Produto")
  public void deveApagarProduto(){
    Produto produto = ProdutoTest.criarProdutoComIdAutomatico();
    Produto produtoSalvo = this.entityManager.persist(produto);
    this.repository.delete(produtoSalvo);
    boolean existeProduto = this.repository.existsById(produtoSalvo.getId());
    assertThat(existeProduto).isFalse();
  }

  @Test
  @DisplayName("Deve salvar um Produto")
  public void deveSalvarProduto(){
    Produto produto = ProdutoTest.criarProdutoComIdAutomatico();
    Produto produtoSalvo = this.entityManager.persist(produto);
    Optional<Produto> produtoRetornado = this.repository.findById(produtoSalvo.getId());

    assertThat(produtoRetornado.get().getId()).isNotNull();
    assertThat(produtoRetornado.get().getId()).isEqualTo(produtoSalvo.getId());
    assertThat(produtoRetornado.get().getNome()).isEqualTo(produtoSalvo.getNome());
    assertThat(produtoRetornado.get().getPreco()).isEqualTo(produtoSalvo.getPreco());
  }
}
