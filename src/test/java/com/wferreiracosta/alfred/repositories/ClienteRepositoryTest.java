package com.wferreiracosta.alfred.repositories;

import com.wferreiracosta.alfred.domain.Cliente;
import com.wferreiracosta.alfred.domain.ObjectDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteRepositoryTest extends RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClienteRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando o cliente existir no banco de dados")
    void retornarVerdadeiroQuandoExistirCliente() {
        Cliente cliente = ObjectDomain.criarClienteSemId();
        Cliente clienteSalvo = this.entityManager.persist(cliente);
        boolean existeCliente = this.repository.existsById(clienteSalvo.getId());
        assertThat(existeCliente).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando o cliente não existir no banco de dados")
    void retornarFalsoQuandoNaoExistirCliente() {
        boolean existeCliente = this.repository.existsById(1);
        assertThat(existeCliente).isFalse();
    }

    @Test
    @DisplayName("Deve buscar cliente no banco de dados por id")
    void deveRetornarClienteSalvoNoBancoDeDados() {
        Cliente cliente = ObjectDomain.criarClienteSemId();
        Cliente clienteSalvo = this.entityManager.persist(cliente);
        Optional<Cliente> clienteRetornado = this.repository.findById(clienteSalvo.getId());
        assertThat(clienteRetornado).isPresent();
    }

    @Test
    @DisplayName("Deve apagar um cliente no banco de dados")
    void deveApagarClienteNoBancoDeDados() {
        Cliente cliente = ObjectDomain.criarClienteSemId();
        Cliente clienteRetornado = this.entityManager.persist(cliente);
        this.repository.delete(clienteRetornado);
        Cliente clienteApagado = this.entityManager.find(Cliente.class, clienteRetornado.getId());
        assertThat(clienteApagado).isNull();
    }

    @Test
    @DisplayName("Deve salvar cliente no banco de dados")
    void deveSalvarClienteNoBancoDeDados() {
        Cliente cliente = ObjectDomain.criarClienteSemId();
        Cliente clienteSalvo = this.repository.save(cliente);
        assertThat(clienteSalvo).isNotNull();
        assertThat(clienteSalvo.getNome()).isEqualTo(cliente.getNome());
        assertThat(clienteSalvo.getEmail()).isEqualTo(cliente.getEmail());
        assertThat(clienteSalvo.getCpfOuCnpj()).isEqualTo(cliente.getCpfOuCnpj());
        assertThat(clienteSalvo.getTipo()).isEqualTo(cliente.getTipo());
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes salva no banco de dados")
    void deveRetornarListaDeCliente() {
        Cliente primeiroCliente = ObjectDomain.criarClienteSemId();
        Cliente segundoCliente = ObjectDomain.criarClienteSemId();
        this.entityManager.persist(primeiroCliente);
        this.entityManager.persist(segundoCliente);
        List<Cliente> listaClientes = this.repository.findAll();

        assertThat(listaClientes)
                .asList()
                .contains(primeiroCliente)
                .contains(segundoCliente)
                .size().isEqualTo(2);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia porque não tem cliente no banco de dados")
    void deveRetornarUmaListaVaziaPorqueNaoTemCliente() {
        List<Cliente> listaCliente = this.repository.findAll();
        assertThat(listaCliente)
                .asList()
                .size().isEqualTo(0);
    }
}
