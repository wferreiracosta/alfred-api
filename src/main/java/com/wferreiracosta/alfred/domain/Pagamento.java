package com.wferreiracosta.alfred.domain;

import com.wferreiracosta.alfred.domain.enums.EstadoPagamento;
import com.wferreiracosta.alfred.domain.enums.TipoCliente;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private Integer estado;
    @OneToOne
    @JoinColumn(name="pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getId();
        this.pedido = pedido;
    }

    public EstadoPagamento getTipo() {
        return EstadoPagamento.toEnum(this.estado);
    }

    public void setTipo(EstadoPagamento estado) {
        this.estado = estado.getId();
    }

}
