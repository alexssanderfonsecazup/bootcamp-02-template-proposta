package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;

import br.com.bootcamp.zup.proposta.cartao.model.Vencimento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class VencimentoResponse {

    @NotBlank
    private String id;

    @NotNull @Positive
    private Integer dia;

    @NotNull
    private LocalDate dataDeCriacao;


    public Vencimento toModel(){
        return new Vencimento();
    }


    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }
}
