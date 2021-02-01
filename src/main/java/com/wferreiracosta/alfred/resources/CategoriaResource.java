package com.wferreiracosta.alfred.resources;

import java.util.Optional;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.resources.exception.ObjectNotFoundException;
import com.wferreiracosta.alfred.service.CategoriaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/categorias")
@RequiredArgsConstructor
@Slf4j
public class CategoriaResource {

  private final CategoriaService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    log.info("[GET] Fazendo a busca da categoria com o id: {}", id);
    Optional<Categoria> categoria = this.service.findById(id);
    log.info("[GET] Retorno: {}", categoria);
    return ResponseEntity.ok().body(categoria.orElseThrow(() -> new ObjectNotFoundException(
      "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
  }

}