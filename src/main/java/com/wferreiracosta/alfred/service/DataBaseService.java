package com.wferreiracosta.alfred.service;

import java.util.Arrays;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataBaseService {
    @Autowired
	private CategoriaRepository categoriaRepository;

    public void instantiateTestDatabase() {
        Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
    }
}
