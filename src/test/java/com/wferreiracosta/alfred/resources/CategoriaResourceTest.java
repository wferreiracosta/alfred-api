package com.wferreiracosta.alfred.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.domain.CategoriaTest;
import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.dto.CategoriaDTO;
import com.wferreiracosta.alfred.dto.CategoriaProdutoDTO;
import com.wferreiracosta.alfred.service.CategoriaService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CategoriaResource.class)
@AutoConfigureMockMvc
public class CategoriaResourceTest {
  static String CATEGORIA_API = "/categorias";

  @Autowired
  MockMvc mvc;

  @MockBean
  CategoriaService service;

  @Test
  @DisplayName("Deve buscar uma categoria existente e retornar ela")
  public void deveBuscarERetornarUmaCategoriaExistente() throws Exception {
    Integer id = 1;
    Categoria categoria = CategoriaTest.criarCategoriaComIdAutomatico();
    categoria.setId(id);

    BDDMockito.given(this.service.findById(id))
      .willReturn(Optional.of(categoria));

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(CATEGORIA_API.concat("/"+id))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
      .andExpect(MockMvcResultMatchers.jsonPath("id").value(categoria.getId()))
      .andExpect(MockMvcResultMatchers.jsonPath("nome").value(categoria.getNome()));
  }

  @Test
  @DisplayName("Deve buscar uma categoria que não existe e retornar o status not_found")
  public void deveBuscarUmaCategoriaQueNaoExistente() throws Exception {
    Integer id = 1;
    String msg = "Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaDTO.class.getName();

    BDDMockito.given(this.service.findById(id))
      .willReturn(Optional.empty());

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(CATEGORIA_API.concat("/"+id))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andExpect(MockMvcResultMatchers.jsonPath("status").value("404"))
      .andExpect(MockMvcResultMatchers.jsonPath("msg").value(msg));
  }

  @Test
  @DisplayName("Deve buscar uma categoria existente e retornar ela é o produto associado")
  public void deveBuscarERetornarUmaCategoriaExistenteEProduto() throws Exception {
    Integer id = 1;

    Categoria categoria = new Categoria(id, "Informática");
    
    Produto produto = new Produto(id, "Computador", 2000.00);

    categoria.getProdutos().addAll(Arrays.asList(produto));
    produto.getCategorias().addAll(Arrays.asList(categoria));

    BDDMockito.given(this.service.findById(id))
      .willReturn(Optional.of(categoria));

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(CATEGORIA_API.concat("/"+id+"/produtos"))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
      .andExpect(MockMvcResultMatchers.jsonPath("id").value(categoria.getId()))
      .andExpect(MockMvcResultMatchers.jsonPath("nome").value(categoria.getNome()))
      .andExpect(MockMvcResultMatchers.jsonPath("produtos").isArray())
      .andExpect(MockMvcResultMatchers.jsonPath("produtos[0]").value(produto))
      .andExpect(MockMvcResultMatchers.jsonPath("produtos", Matchers.hasSize(1)));
  }

  @Test
  @DisplayName("Deve buscar uma categoria existente que não possua produtos")
  public void deveBuscarERetornarUmaCategoriaExistenteENaoTemProduto() throws Exception {
    Integer id = 1;

    Categoria categoria = new Categoria(id, "Informática");
    
    BDDMockito.given(this.service.findById(id))
      .willReturn(Optional.of(categoria));

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(CATEGORIA_API.concat("/"+id+"/produtos"))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
      .andExpect(MockMvcResultMatchers.jsonPath("id").value(categoria.getId()))
      .andExpect(MockMvcResultMatchers.jsonPath("nome").value(categoria.getNome()))
      .andExpect(MockMvcResultMatchers.jsonPath("produtos").isArray())
      .andExpect(MockMvcResultMatchers.jsonPath("produtos", Matchers.hasSize(0)));
  }

  @Test
  @DisplayName("Deve buscar categoria que não existe no endpoint que retorna produtos")
  public void deveBuscarCategoriaQueNaoExisteNoEndpointQueRetornaProdutos() throws Exception {
    Integer id = 1;
    String msg = "Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaProdutoDTO.class.getName();

    BDDMockito.given(this.service.findById(id))
      .willReturn(Optional.empty());

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(CATEGORIA_API.concat("/"+id+"/produtos"))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

      this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andExpect(MockMvcResultMatchers.jsonPath("status").value("404"))
      .andExpect(MockMvcResultMatchers.jsonPath("msg").value(msg));
  }

  @Test
  @DisplayName("Deve retornar todas as categorias cadastradas")
  public void deveRetornarTodasAsCategoriasCadastradas() throws Exception {
    Integer id = 1;
    Categoria categoria = new Categoria(id, "Informática");
    List<Categoria> listaCategoria = new ArrayList<>();
    listaCategoria.add(categoria);

    BDDMockito.given(this.service.findAll())
      .willReturn(listaCategoria);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(CATEGORIA_API)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);
    
    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("[0]id").value(categoria.getId()))
      .andExpect(MockMvcResultMatchers.jsonPath("[0]nome").value(categoria.getNome()));
  }

  @Test
  @DisplayName("Deve retornar uma lista vazia porque não tem categoria cadastrada")
  public void deveRetornarUmaListaVazia() throws Exception {
    List<Categoria> listaCategoria = new ArrayList<>();
    BDDMockito.given(this.service.findAll())
      .willReturn(listaCategoria);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(CATEGORIA_API)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
      .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
  }
  
}
