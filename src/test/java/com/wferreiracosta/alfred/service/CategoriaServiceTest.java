package com.wferreiracosta.alfred.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.domain.CategoriaTest;
import com.wferreiracosta.alfred.repositories.CategoriaRepository;
import com.wferreiracosta.alfred.service.impl.CategoriaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CategoriaServiceTest {

  CategoriaService service;

  @MockBean
  CategoriaRepository repository;

  @BeforeEach
  public void setUp() {
      this.service = new CategoriaServiceImpl(repository);
  }

  @Test
  @DisplayName("Deve buscar uma categoria por id")
  public void buscarCategoriaPorId(){
    Integer id = 1;
    Categoria categoria = CategoriaTest.criarCategoriaComIdAutomatico();
    categoria.setId(id);

    Mockito.when(this.repository.findById(id)).thenReturn(Optional.of(categoria));

    Optional<Categoria> categoriaRetornada = this.service.findById(id);

    assertThat(categoriaRetornada.isPresent()).isTrue();
    assertThat(categoriaRetornada.get().getId()).isEqualTo(categoria.getId());
    assertThat(categoriaRetornada.get().getNome()).isEqualTo(categoria.getNome());
  }

  @Test
  @DisplayName("Deve buscar uma categoria que não existe por id")
  public void buscarCategoriaQueNaoExistePorId(){
    Integer id = 1;

    Mockito.when(this.repository.findById(id)).thenReturn(Optional.empty());

    Optional<Categoria> categoriaRetornada = this.service.findById(id);

    assertThat(categoriaRetornada.isPresent()).isFalse();
  }

  @Test
  @DisplayName("Deve retornar todas as categorias no banco de dados, não deve retornar os produtos")
  public void buscarTodasAsCategoriasENaoTrasOsProdutos(){
    Integer id = 1;
    Categoria categoria = CategoriaTest.criarCategoriaComIdAutomatico();
    categoria.setId(id);
    this.repository.save(categoria);

    List<Categoria> lista = new ArrayList<>();
    lista.add(categoria);

    Mockito.when(this.repository.findAll()).thenReturn(lista);

    List<Categoria> listaRetornada = this.service.findAll();

    assertThat(listaRetornada).asList();
    assertThat(listaRetornada.get(0)).isEqualTo(lista.get(0));
  }

  @Test
  @DisplayName("Deve retornar uma lista vazia porque não vai ter categoria cadastrada")
  public void deveRetornarUmaListaVaziaPorqueNaoVaiTerCategoria(){
    List<Categoria> lista = new ArrayList<>();
    Mockito.when(this.repository.findAll()).thenReturn(lista);
    List<Categoria> listaRetornada = this.service.findAll();

    assertThat(listaRetornada).asList();
    assertThat(listaRetornada).isEqualTo(lista);
  }

}
