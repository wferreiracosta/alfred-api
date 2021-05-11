package com.wferreiracosta.alfred.dto;

import com.wferreiracosta.alfred.domain.Produto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class CategoriaProdutoDTO {
  
  private Integer id;
  private String nome;

  @ToString.Exclude
  private List<Produto> produtos = new ArrayList<>();

}