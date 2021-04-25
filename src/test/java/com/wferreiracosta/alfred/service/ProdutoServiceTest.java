package com.wferreiracosta.alfred.service;

import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.domain.ProdutoTest;
import com.wferreiracosta.alfred.repositories.ProdutoRepository;
import com.wferreiracosta.alfred.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoServiceTest extends ServiceTest {

  ProdutoService service;

  @MockBean
  ProdutoRepository repository;

  @BeforeEach
  public void setUp(){
    this.service = new ProdutoServiceImpl(repository);
  }

  @Test
  @DisplayName("Deve retornar um Produto")
  public void deveRetornarProduto(){
    Integer id = 1;
    Produto produto = ProdutoTest.criarProdutoComIdAutomatico();
    produto.setId(id);

    Mockito.when(this.repository.findById(id)).thenReturn(Optional.of(produto));
    Optional<Produto> produtoRetornado = this.service.findById(id);

    assertThat(produtoRetornado.isPresent()).isTrue();
    assertThat(produtoRetornado.get().getId()).isEqualTo(produto.getId());
    assertThat(produtoRetornado.get().getNome()).isEqualTo(produto.getNome());
    assertThat(produtoRetornado.get().getPreco()).isEqualTo(produto.getPreco());
  }

  @Test
  @DisplayName("Não deve retornar Produto")
  public void naoDeveRetornarProduto(){
    Integer id = 1;

    Mockito.when(this.repository.findById(id)).thenReturn(Optional.empty());
    Optional<Produto> produtoRetornado = this.service.findById(id);

    assertThat(produtoRetornado.isPresent()).isFalse();
  }
}
