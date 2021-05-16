package com.wferreiracosta.alfred.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Canceldo");

    private final Integer id;
    private final String descricao;

    public static EstadoPagamento toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
            if (id.equals(estadoPagamento.getId())) {
                return estadoPagamento;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
