package com.wferreiracosta.alfred.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoCliente {

    PESSOAFISICA(0, "Pessoa Física"),
    PESSOAJURIDICA(1, "Pessoa Jurídica");

    private Integer id;
    private String descricao;

    public static TipoCliente toEnum(Integer id){
        if(id == null){
            return null;
        }
        for (TipoCliente tipoCliente : TipoCliente.values()){
            if (id.equals(tipoCliente.getId())){
                return tipoCliente;
            }
        }
        throw new IllegalArgumentException("Id inválido: "+id);
    }

}
