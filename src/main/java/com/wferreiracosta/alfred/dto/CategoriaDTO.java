package com.wferreiracosta.alfred.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CategoriaDTO {
  
  private Integer id;
  private String nome;

}
