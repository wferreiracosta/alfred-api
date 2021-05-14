package com.wferreiracosta.alfred.domain;

public class ProdutoTest {

    public static Produto criarProdutoSemId() {
        return Produto.builder()
                .nome("Computador")
                .preco(10.00)
                .build();
    }

    public static Produto criarProdutoComId() {
        return Produto.builder()
                .id(1)
                .nome("Computador")
                .preco(10.00)
                .build();
    }

}
