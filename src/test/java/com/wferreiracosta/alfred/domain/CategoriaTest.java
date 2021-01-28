package com.wferreiracosta.alfred.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CategoriaTest {
    private final String NOME = "Escritorio";
    private final Integer ID = 1;

    @Test
    public void criarObjetoCategoria(){
        Categoria categoria = Categoria.builder()
            .id(ID)
            .nome(NOME)
            .build();
        
        assertThat(categoria.getId()).isEqualTo(ID);
        assertThat(categoria.getNome()).isEqualTo(NOME);
    }

    @Test
    public void criarObjetoCategoriaPeloMetodoSet(){
        Categoria categoria = new Categoria();
        categoria.setId(ID);
        categoria.setNome(NOME);
        
        assertThat(categoria.getId()).isEqualTo(ID);
        assertThat(categoria.getNome()).isEqualTo(NOME);
    }

    @Test
    public void testandoDuasCategoriasNaoSaoIguaisComEquals(){
        Categoria categoria1 = new Categoria();
        categoria1.setId(ID);
        categoria1.setNome(NOME);

        Categoria categoria2 = new Categoria(ID+1, NOME);

        assertThat(categoria1.equals(categoria2)).isFalse();
    }

    @Test
    public void testandoDuasCategoriasQueSaoIguaisComEquals(){
        Categoria categoria1 = Categoria.builder()
            .id(ID)
            .nome(NOME)
            .build();

        Categoria categoria2 = new Categoria(ID, NOME);

        assertThat(categoria1.equals(categoria2)).isTrue();
    }

    @Test
    public void testandoSeDuasCategoriasSaoIguaisComHashCode(){
        Categoria categoria1 = new Categoria();
        categoria1.setId(ID);
        categoria1.setNome(NOME);

        Categoria categoria2 = new Categoria(ID+1, NOME);

        assertThat(categoria1.hashCode()).isNotEqualTo(categoria2.hashCode());
    }

    @Test
    public void testandoToStringDaClasseCategoria(){
        Categoria categoria = new Categoria();
        categoria.setId(ID);
        categoria.setNome(NOME);

        assertThat(categoria.toString()).contains(Integer.toString(ID));
        assertThat(categoria.toString()).contains(NOME);
    }
}
