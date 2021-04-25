package com.wferreiracosta.alfred.service.impl;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.repositories.CategoriaRepository;
import com.wferreiracosta.alfred.repositories.ProdutoRepository;
import com.wferreiracosta.alfred.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataBaseServiceImpl implements DataBaseService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void instantiateTestDatabase() {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Toalha", 80.00);
        Produto p4 = new Produto(null, "Calculadora", 50.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p3));
        cat4.getProdutos().addAll(Arrays.asList(p4));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat3));
        p4.getCategorias().addAll(Arrays.asList(cat4));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
    }
}
