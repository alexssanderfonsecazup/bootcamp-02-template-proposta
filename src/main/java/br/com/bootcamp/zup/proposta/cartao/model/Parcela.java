package br.com.bootcamp.zup.proposta.cartao.model;

import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.ParcelaResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


@Entity
public class Parcela {

    @Id
    @NotBlank
    private String id;

    @NotNull
    private Integer quantidade;

    @NotNull @Positive
    private BigDecimal valor;


    @Deprecated
    public Parcela(){ }


    public Parcela(@NotBlank String id, @NotNull Integer quantidade, @NotNull @Positive BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcela(ParcelaResponse parcelaResponse){
        this.id = parcelaResponse.getId();
        this.quantidade = parcelaResponse.getQuantidade();
        this.valor = parcelaResponse.getValor();
    }
}
