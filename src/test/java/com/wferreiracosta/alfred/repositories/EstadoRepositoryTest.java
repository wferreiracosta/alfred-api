package com.wferreiracosta.alfred.repositories;

import com.wferreiracosta.alfred.domain.Categoria;
import com.wferreiracosta.alfred.domain.CategoriaTest;
import com.wferreiracosta.alfred.domain.Estado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EstadoRepositoryTest extends RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EstadoRepository repository;

    public Estado criarEstadoSemId(){
        return Estado.builder().nome("São Paulo").build();
    }

    @Test
    @DisplayName("Deve retornar verdadeiro quando estado existir")
    public void retornarVerdadeiroQuandoExistirEstado(){
        Estado estado = this.criarEstadoSemId();
        Estado estadoSalvo = this.entityManager.persist(estado);
        boolean existeEstado = this.repository.existsById(estadoSalvo.getId());
        assertThat(existeEstado).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando o estado não existir")
    public void retornarFalsoQuandoNaoExistirEstado(){
        Integer id = 1;
        boolean existeEstado = this.repository.existsById(id);
        assertThat(existeEstado).isFalse();
    }

    @Test
    @DisplayName("Deve obter um estado por id")
    public void deveRetornaEstado(){
        Estado estado = this.criarEstadoSemId();
        Estado estadoSalvo = this.entityManager.persist(estado);
        Optional<Estado> estadoRetornado = this.repository.findById(estadoSalvo.getId());
        assertThat(estadoRetornado.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve apagar um estado")
    public void deveApagarUmEstado(){
        Estado estado = this.criarEstadoSemId();
        this.entityManager.persist(estado);
        Estado estadoRetornado = this.entityManager.find(Estado.class, estado.getId());
        this.repository.delete(estadoRetornado);
        Estado estadoApagado = this.entityManager.find(Estado.class, estado.getId());
        assertThat(estadoApagado).isNull();
    }

    @Test
    @DisplayName("Deve salvar um estado")
    public void salvarEstado(){
        Estado estado = this.criarEstadoSemId();
        Estado estadoSalvo = this.repository.save(estado);
        assertThat(estadoSalvo.getId()).isNotNull();
        assertThat(estadoSalvo.getId()).isEqualTo(estado.getId());
        assertThat(estadoSalvo.getNome()).isEqualTo(estado.getNome());
    }

}
