package com.wferreiracosta.alfred.domain;

import com.wferreiracosta.alfred.domain.enums.TipoCliente;

public class ObjectDomain {

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

    public static Cliente criarClienteSemId() {
        return Cliente.builder()
                .nome("Pedro")
                .cpfOuCnpj("18683776018")
                .email("pedro@silva.com")
                .tipo(TipoCliente.PESSOAFISICA.getId())
                .build();
    }

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

    public static Cliente criarClienteComId() {
        return Cliente.builder()
                .id(1)
                .nome("Pedro")
                .cpfOuCnpj("18683776018")
                .email("pedro@silva.com")
                .tipo(TipoCliente.PESSOAFISICA.getId())
                .build();
    }

    public static Endereco criarEnderecoSemId() {
        return Endereco.builder()
                .logradouro("Rua")
                .numero("666")
                .complemento("Lote 5")
                .bairro("Liberdade")
                .cep("32569123")
                .build();
    }

}
