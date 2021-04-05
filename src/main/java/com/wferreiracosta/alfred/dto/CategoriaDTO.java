package com.wferreiracosta.alfred.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CategoriaDTO {
  
  private Integer id;
  private String nome;

}
