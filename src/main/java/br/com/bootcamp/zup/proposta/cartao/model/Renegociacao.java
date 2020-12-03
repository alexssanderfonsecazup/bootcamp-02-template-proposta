package br.com.bootcamp.zup.proposta.cartao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    @NotBlank
    private String id;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Renegociacao(){}

    public Renegociacao(@NotBlank String id, @NotNull @Positive Integer quantidade,
                        @NotNull @Positive BigDecimal valor,
                        @NotNull LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }


}
