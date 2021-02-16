package com.wferreiracosta.alfred.dto;

import java.util.ArrayList;
import java.util.List;

import com.wferreiracosta.alfred.domain.Produto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
public class CategoriaDTO {
  
  private Integer id;
  private String nome;

  @ToString.Exclude
  private List<Produto> produtos = new ArrayList<>();

}
