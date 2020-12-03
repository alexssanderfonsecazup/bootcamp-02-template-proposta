package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;

import br.com.bootcamp.zup.proposta.cartao.model.Parcela;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ParcelaResponse {
    @NotBlank
    private String id;

    @NotNull
    private Integer quantidade;

    @NotNull @Positive
    private BigDecimal valor;

    public static List<Parcela> toModelList(List<ParcelaResponse> parcelas) {
        return parcelas.stream().map(Parcela::new)
                .collect(Collectors.toList());
    }

    public Parcela toModel(){
        return new Parcela(id,quantidade,valor);
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
}
