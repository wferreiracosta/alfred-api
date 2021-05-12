package com.wferreiracosta.alfred.repositories;

import com.wferreiracosta.alfred.domain.Cidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CidadeRepositoryTest extends RepositoryTest{

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CidadeRepository repository;

    public Cidade criarCidadeSemId(){
        return Cidade.builder().nome("São Paulo").build();
    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando cidade existir")
    public void retornarVerdadeiroQuandoExistirCidade(){
        Cidade cidade = this.criarCidadeSemId();
        Cidade cidadeSalvo = this.entityManager.persist(cidade);
        boolean existeCidade = this.repository.existsById(cidadeSalvo.getId());
        assertThat(existeCidade).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando o cidade não existir")
    public void retornarFalsoQuandoNaoExistirCidade(){
        Integer id = 1;
        boolean existeCidade = this.repository.existsById(id);
        assertThat(existeCidade).isFalse();
    }

    @Test
    @DisplayName("Deve obter um cidade por id")
    public void deveRetornaCidade(){
        Cidade cidade = this.criarCidadeSemId();
        Cidade cidadeSalvo = this.entityManager.persist(cidade);
        Optional<Cidade> cidadeRetornado = this.repository.findById(cidadeSalvo.getId());
        assertThat(cidadeRetornado.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve apagar um cidade")
    public void deveApagarUmCidade(){
        Cidade cidade = this.criarCidadeSemId();
        this.entityManager.persist(cidade);
        Cidade cidadeRetornado = this.entityManager.find(Cidade.class, cidade.getId());
        this.repository.delete(cidadeRetornado);
        Cidade cidadeApagado = this.entityManager.find(Cidade.class, cidade.getId());
        assertThat(cidadeApagado).isNull();
    }

    @Test
    @DisplayName("Deve salvar um cidade")
    public void salvarCidade(){
        Cidade cidade = this.criarCidadeSemId();
        Cidade cidadeSalvo = this.repository.save(cidade);
        assertThat(cidadeSalvo.getId()).isNotNull();
        assertThat(cidadeSalvo.getId()).isEqualTo(cidade.getId());
        assertThat(cidadeSalvo.getNome()).isEqualTo(cidade.getNome());
    }
}
