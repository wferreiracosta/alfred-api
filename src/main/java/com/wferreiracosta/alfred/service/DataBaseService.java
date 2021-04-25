package com.wferreiracosta.alfred.service;

import java.util.Arrays;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.repositories.CategoriaRepository;
import com.wferreiracosta.alfred.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface DataBaseService {

	public void instantiateTestDatabase();

}
