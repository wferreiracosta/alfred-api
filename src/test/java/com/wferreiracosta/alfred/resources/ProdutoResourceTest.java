package com.wferreiracosta.alfred.resources;

import com.wferreiracosta.alfred.domain.Produto;
import com.wferreiracosta.alfred.domain.ProdutoTest;
import com.wferreiracosta.alfred.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(controllers = ProdutoResource.class)
public class ProdutoResourceTest extends ControllerTest {
  static final String PRODUTO_API = "/produtos";

  @Autowired
  MockMvc mvc;

  @MockBean
  ProdutoService service;

  @Test
  @DisplayName("Deve buscar e retornar o produto que existe relacionado a id")
  public void deveBuscarERetornarProdutoQueExiste() throws Exception {
    Integer id = 1;
    Produto produto = ProdutoTest.criarProdutoComIdAutomatico();
    produto.setId(id);

    BDDMockito.given(this.service.findById(id))
      .willReturn(Optional.of(produto));

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(PRODUTO_API.concat("/"+id))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
      .andExpect(MockMvcResultMatchers.jsonPath("id").value(produto.getId()))
      .andExpect(MockMvcResultMatchers.jsonPath("nome").value(produto.getNome()))
      .andExpect(MockMvcResultMatchers.jsonPath("preco").value(produto.getPreco()));
  }

  @Test
  @DisplayName("Deve buscar um produto que não existe e retornar erro 404")
  public void deveBuscarUmProdutoQueNaoExistente() throws Exception {
    Integer id = 1;
    String msg = "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName();

    BDDMockito.given(this.service.findById(id))
      .willReturn(Optional.empty());

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .get(PRODUTO_API.concat("/"+id))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    this.mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andExpect(MockMvcResultMatchers.jsonPath("status").value("404"))
      .andExpect(MockMvcResultMatchers.jsonPath("msg").value(msg));
  }

}
