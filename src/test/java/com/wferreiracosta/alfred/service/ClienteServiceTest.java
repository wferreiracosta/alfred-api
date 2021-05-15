package com.wferreiracosta.alfred.service;

import com.wferreiracosta.alfred.domain.Cliente;
import com.wferreiracosta.alfred.domain.ObjectDomain;
import com.wferreiracosta.alfred.repositories.ClienteRepository;
import com.wferreiracosta.alfred.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteServiceTest extends ServiceTest {

    private ClienteService service;

    @MockBean
    private ClienteRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new ClienteServiceImpl(this.repository);
    }

    @Test
    @DisplayName("Deve buscar cliente pelo id e retornar ele")
    void buscarCLientePeloId() {
        Cliente cliente = ObjectDomain.criarClienteComId();

        Mockito
                .when(this.repository.findById(cliente.getId()))
                .thenReturn(Optional.of(cliente));

        Optional<Cliente> clienteRetornado = this.service.findById(cliente.getId());

        assertThat(clienteRetornado).isPresent();
        assertThat(clienteRetornado.get().getId()).isEqualTo(cliente.getId());
        assertThat(clienteRetornado.get().getTipo()).isEqualTo(cliente.getTipo());
        assertThat(clienteRetornado.get().getNome()).isEqualTo(cliente.getNome());
        assertThat(clienteRetornado.get().getEmail()).isEqualTo(cliente.getEmail());
        assertThat(clienteRetornado.get().getCpfOuCnpj()).isEqualTo(cliente.getCpfOuCnpj());
    }

    @Test
    @DisplayName("Deve retornar vazio porque procurou um cliente que não está cadastrado")
    void deveRetornarVazioPorqueClienteProcuradoNaoExiste() {
        Integer id = 1;

        Mockito
                .when(this.repository.findById(id))
                .thenReturn(Optional.empty());

        Optional<Cliente> clienteRetornado = this.service.findById(id);

        assertThat(clienteRetornado).isNotPresent();
    }
}
