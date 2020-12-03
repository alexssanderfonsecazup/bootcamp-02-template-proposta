package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;

import br.com.bootcamp.zup.proposta.cartao.model.Renegociacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoResponse {

    @NotBlank
    private String id;

    @NotNull @Positive
    private Integer quantidade;

    @NotNull @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataDeCriacao;


    public Renegociacao toModel(){
        return new Renegociacao(id,quantidade,valor,dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
