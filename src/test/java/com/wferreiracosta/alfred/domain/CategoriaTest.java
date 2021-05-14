package com.wferreiracosta.alfred.domain;

public class CategoriaTest {

    public static Categoria criarCategoriaSemId() {
        return Categoria.builder()
            .nome("Categoria Teste")
            .build();
    }

    public static Categoria criarCategoriaComId() {
        return Categoria.builder()
            .id(1)
            .nome("Categoria Teste")
            .build();
    }

}
