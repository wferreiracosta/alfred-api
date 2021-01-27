package com.wferreiracosta.alfred.resources;

import java.util.ArrayList;
import java.util.List;

import com.wferreiracosta.alfred.domain.Categoria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> listar() {
        Categoria cat1 = Categoria
            .builder()
            .id(1)
            .nome("Informática")
            .build();

        Categoria cat2 = Categoria
            .builder()
            .id(2)
            .nome("Escritorio")
            .build();

        List<Categoria> categorias = new ArrayList<>();
        categorias.add(cat1);
        categorias.add(cat2);

        return categorias;
    }

}