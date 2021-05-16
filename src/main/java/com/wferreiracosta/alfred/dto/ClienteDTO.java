package com.wferreiracosta.alfred.dto;

import com.wferreiracosta.alfred.domain.Endereco;
import com.wferreiracosta.alfred.domain.enums.TipoCliente;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private TipoCliente tipo;
    private Set<String> telefones = new HashSet<>();
    private List<Endereco> enderecos = new ArrayList<>();
}
