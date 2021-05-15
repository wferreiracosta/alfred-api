package com.wferreiracosta.alfred.repositories;

import com.wferreiracosta.alfred.domain.Cliente;
import com.wferreiracosta.alfred.domain.Endereco;
import com.wferreiracosta.alfred.domain.ObjectDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoRepositoryTest extends RepositoryTest{

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EnderecoRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir endereço")
    void deveRetornarVerdadeiroQuandoExistirEndereco(){
        Endereco endereco = ObjectDomain.criarEnderecoSemId();
        Endereco enderecoSalvo = this.entityManager.persist(endereco);
        boolean enderecoRetornado = this.repository.existsById(enderecoSalvo.getId());
        assertThat(enderecoRetornado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando não existir o endereco")
    void retornarFalsoQuandoNaoExistirEndereco(){
        boolean enderecoRetornado = this.repository.existsById(1);
        assertThat(enderecoRetornado).isFalse();
    }

    @Test
    @DisplayName("Deve retornar endereco salvo no banco de dados")
    void deveRetornarEnderecoSalvoNoBancoDeDados(){
        Endereco endereco = ObjectDomain.criarEnderecoSemId();
        Endereco enderecoSalvo = this.entityManager.persist(endereco);
        Optional<Endereco> enderecoRetornado = this.repository.findById(enderecoSalvo.getId());
        assertThat(enderecoRetornado).isPresent();
    }

    @Test
    @DisplayName("Deve apagar endereço no banco de dados")
    void deveApagarEnderecoNoBancoDeDados(){
        Endereco endereco = ObjectDomain.criarEnderecoSemId();
        Endereco enderecoSalvo = this.entityManager.persist(endereco);
        this.repository.deleteById(enderecoSalvo.getId());
        Optional<Endereco> enderecoRetornado = this.repository.findById(enderecoSalvo.getId());
        assertThat(enderecoRetornado).isNotPresent();
    }

    @Test
    @DisplayName("Deve salvo endereco no banco de dados")
    void deveSalvoEnderecoNoBancoDeDados(){
        Endereco endereco = ObjectDomain.criarEnderecoSemId();
        Endereco enderecoSalvo = this.repository.save(endereco);
        assertThat(enderecoSalvo.getBairro()).isEqualTo(endereco.getBairro());
        assertThat(enderecoSalvo.getCep()).isEqualTo(endereco.getCep());
        assertThat(enderecoSalvo.getComplemento()).isEqualTo(endereco.getComplemento());
        assertThat(enderecoSalvo.getLogradouro()).isEqualTo(endereco.getLogradouro());
        assertThat(enderecoSalvo.getNumero()).isEqualTo(endereco.getNumero());
    }

    @Test
    @DisplayName("Deve retornar uma lista de endereços salva no banco de dados")
    void deveRetornarUmaListaDeEnderecos(){
        Endereco primeiroEndereco = ObjectDomain.criarEnderecoSemId();
        Endereco segundoEndereco = ObjectDomain.criarEnderecoSemId();
        this.entityManager.persist(primeiroEndereco);
        this.entityManager.persist(segundoEndereco);
        List<Endereco> listaEnderecos = this.repository.findAll();
        assertThat(listaEnderecos)
                .asList()
                .contains(primeiroEndereco)
                .contains(segundoEndereco)
                .size().isEqualTo(2);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia porque não tem endereço no banco de dados")
    void deveRetornarUmaListaVaziaPorqueNaoTemEndereco() {
        List<Endereco> listaEnderecos = this.repository.findAll();
        assertThat(listaEnderecos)
                .asList()
                .size().isEqualTo(0);
    }
}
