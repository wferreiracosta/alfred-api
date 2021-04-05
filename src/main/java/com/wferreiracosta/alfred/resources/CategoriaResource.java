package com.wferreiracosta.alfred.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.wferreiracosta.alfred.dto.CategoriaDTO;
import com.wferreiracosta.alfred.dto.CategoriaProdutoDTO;
import com.wferreiracosta.alfred.resources.exception.ObjectNotFoundException;
import com.wferreiracosta.alfred.service.CategoriaService;

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
  public CategoriaDTO findById(@PathVariable Integer id) {
    log.info("[GET] Fazendo a busca da categoria com o id: {}", id);
    return this.service.findById(id).map(categoria -> {
      return CategoriaDTO
        .builder()
        .id(categoria.getId())
        .nome(categoria.getNome())
        .build();
    }).orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaDTO.class.getName()));
  }

  @GetMapping(value = "/{id}/produtos")
  public CategoriaProdutoDTO findByIdWithProducts(@PathVariable Integer id) {
    log.info("[GET] Fazendo a busca da categoria com produtos usando o id: {}", id);
    return this.service.findById(id).map(categoria -> {
      return CategoriaProdutoDTO
        .builder()
        .id(categoria.getId())
        .nome(categoria.getNome())
        .produtos(categoria.getProdutos())
        .build();
    }).orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaProdutoDTO.class.getName()));
  }

  @GetMapping
  public List<CategoriaDTO> findAll(){
    return this.service.findAll()
      .stream()
      .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome()))
      .collect(Collectors.toList());
  }

}