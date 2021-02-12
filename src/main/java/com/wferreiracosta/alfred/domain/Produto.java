package com.wferreiracosta.alfred.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;
  private String nome;
  private Double preco;

  public Produto(Integer id, String nome, Double preco) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
  }

}
