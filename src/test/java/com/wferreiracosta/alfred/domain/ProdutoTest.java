package com.wferreiracosta.alfred.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProdutoTest {

  private final Integer ID = 1;
  private final String NOME = "";
  private final Double PRECO = 10.00;

  @Test
  @DisplayName("Deve criar um objeto Produto atraves do Builder")
  public void deveCriarObjetoProdutoUsandoBuilder(){
    Produto produto = Produto.builder()
      .id(ID)
      .nome(NOME)
      .preco(PRECO)
      .build();

      assertThat(produto.getId()).isEqualTo(ID);
      assertThat(produto.getNome()).isEqualTo(NOME);
      assertThat(produto.getPreco()).isEqualTo(PRECO);
  }

  @Test
  @DisplayName("Deve criar um objeto Produto usando o Set")
  public void deveCriarObjetoProdutoUsandoSet(){
    Produto produto = new Produto();
    produto.setId(ID);
    produto.setNome(NOME);
    produto.setPreco(PRECO);

    assertThat(produto.getId()).isEqualTo(ID);
    assertThat(produto.getNome()).isEqualTo(NOME);
    assertThat(produto.getPreco()).isEqualTo(PRECO);
  }

  @Test
  @DisplayName("Deve criar um objeto Produto usando o construtor")
  public void deveCriarObjetoUsandoConstrutor(){
    Produto produto = new Produto(ID, NOME, PRECO);

    assertThat(produto.getId()).isEqualTo(ID);
    assertThat(produto.getNome()).isEqualTo(NOME);
    assertThat(produto.getPreco()).isEqualTo(PRECO);
  }

  @Test
  @DisplayName("Deve retornar Falso na comparação doss dois Produtos usando equals")
  public void deveRetornarFalsoNaCompracaoDoisProdutosComEquals(){
    Produto produto1 = new Produto(ID, NOME, PRECO);
    Produto produto2 = new Produto(ID+1, NOME, PRECO);
    assertThat(produto1.equals(produto2)).isFalse();
  }

  @Test
  @DisplayName("Deve retornar Verdadeiro na comparação doss dois Produtos usando equals")
  public void deveRetornarVerdadeiroNaCompracaoDoisProdutosComEquals(){
    Produto produto1 = new Produto(ID, NOME, PRECO);
    Produto produto2 = new Produto(ID, NOME, PRECO);
    assertThat(produto1.equals(produto2)).isTrue();
  }

  @Test
  @DisplayName("Deve retornar falso na comparação dos dois Produtos usando hashCode")
  public void deveRetornarFalsoNaCompracaoDoisProdutosComHashCode(){
    Produto produto1 = new Produto(ID, NOME, PRECO);
    Produto produto2 = new Produto(ID+1, NOME, PRECO);
    assertThat(produto1.hashCode()).isNotEqualTo(produto2.hashCode());
  }

  @Test
  @DisplayName("Deve retornar verdadeiro na comparação dos dois Produtos usando hashCode")
  public void deveRetornarVerdadeiroNaCompracaoDoisProdutosComHashCode(){
    Produto produto1 = new Produto(ID, NOME, PRECO);
    Produto produto2 = new Produto(ID, NOME, PRECO);
    assertThat(produto1.hashCode()).isEqualTo(produto2.hashCode());
  }

  @Test
  @DisplayName("Deve encontrar valores no toString do Produto")
  public void deveEncontrarValoresNoToStringDaClasseProduto(){
    Produto produto = new Produto(ID, NOME, PRECO);

      assertThat(produto.toString()).contains(Integer.toString(ID));
      assertThat(produto.toString()).contains(NOME);
      assertThat(produto.toString()).contains(PRECO.toString());
  }

  @Test
  @DisplayName("Deve encontrar valores no toString com Builder do Produto")
  public void deveEncontrarValoresNoToStringComBuilderDaClasseProduto(){
      String produtoToString = Produto.builder()
          .id(ID)
          .nome(NOME)
          .preco(PRECO)
          .toString();

      assertThat(produtoToString).contains(Integer.toString(ID));
      assertThat(produtoToString).contains(NOME);
      assertThat(produtoToString).contains(PRECO.toString());
  }

}
