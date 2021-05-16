package com.wferreiracosta.alfred.controllers;

import com.wferreiracosta.alfred.domain.Cliente;
import com.wferreiracosta.alfred.domain.Endereco;
import com.wferreiracosta.alfred.domain.ObjectDomain;
import com.wferreiracosta.alfred.dto.CategoriaDTO;
import com.wferreiracosta.alfred.dto.ClienteDTO;
import com.wferreiracosta.alfred.service.ClienteService;
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

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest extends ControllerTest {
    static String CLIENTE_API = "/clientes";

    @Autowired
    MockMvc mvc;

    @MockBean
    ClienteService service;

    @Test
    @DisplayName("Deve retornar um cliente cadastrado")
    void deveRetornarUmClienteCadastrado() throws Exception {
        Cliente cliente = ObjectDomain.criarClienteComId();
        BDDMockito
                .when(this.service.findById(cliente.getId()))
                .thenReturn(Optional.of(cliente));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(CLIENTE_API.concat("/"+cliente.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(cliente.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(cliente.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("cpfOuCnpj").value(cliente.getCpfOuCnpj()))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value(cliente.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("tipo").value(cliente.getTipo().toString()));
    }

    @Test
    @DisplayName("Deve retornar erro porque não encontro cliente")
    void deveRetornarErroPorqueNaoEncontroCliente() throws Exception {
        Integer id = 1;
        String msg = "Objeto não encontrado! id: " + id + " Tipo:" + ClienteDTO.class.getName();
        BDDMockito
                .given(this.service.findById(id))
                .willReturn(Optional.empty());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(CLIENTE_API.concat("/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        this.mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("404"))
                .andExpect(MockMvcResultMatchers.jsonPath("msg").value(msg));
    }

}
