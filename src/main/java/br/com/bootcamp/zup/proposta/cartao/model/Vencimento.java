package br.com.bootcamp.zup.proposta.cartao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Vencimento {

    @Id
    @NotBlank
    private String id;

    @NotNull
    @Positive
    private Integer dia;

    @NotNull
    private LocalDate dataDeCriacao;

    @Deprecated
    public Vencimento(){}

    public Vencimento(@NotBlank String id, @NotNull @Positive Integer dia, @NotNull LocalDate dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
