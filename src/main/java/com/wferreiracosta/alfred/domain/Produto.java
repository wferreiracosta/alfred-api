package com.wferreiracosta.alfred.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;
  private String nome;
  private Double preco;

  @ToString.Exclude
  @Singular
  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "PRODUTO_CATEGORIA",
    joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id")
  )
  private List<Categoria> categorias = new ArrayList<>();

  public Produto(Integer id, String nome, Double preco) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
  }

}
