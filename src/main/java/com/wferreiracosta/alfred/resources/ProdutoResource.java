package com.wferreiracosta.alfred.resources;

import java.util.Optional;

import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.resources.exception.ObjectNotFoundException;
import com.wferreiracosta.alfred.service.ProdutoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/produtos")
@RequiredArgsConstructor
@Slf4j
public class ProdutoResource {

  private final ProdutoService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id){
    log.info("[GET] Fazendo a busca do produto com o id: {}", id);
    Optional<Produto> produto = this.service.findById(id);
    log.info("[GET] Retorno: {}", produto);
    return ResponseEntity.ok().body(produto.orElseThrow(() -> new ObjectNotFoundException(
      "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())));
  }
}
