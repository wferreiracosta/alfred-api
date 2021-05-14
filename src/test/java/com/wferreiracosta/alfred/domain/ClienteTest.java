package com.wferreiracosta.alfred.domain;

import com.wferreiracosta.alfred.domain.enums.TipoCliente;

public class ClienteTest {

    public static Cliente criarClienteSemId() {
        return Cliente.builder()
                .nome("Pedro")
                .cpfOuCnpj("18683776018")
                .email("pedro@silva.com")
                .tipo(TipoCliente.PESSOAFISICA.getId())
                .build();
    }

}
