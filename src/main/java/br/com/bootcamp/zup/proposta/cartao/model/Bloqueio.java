package br.com.bootcamp.zup.proposta.cartao.model;

import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.BloqueioResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class Bloqueio {

    @Id
    private String id;
    @NotNull
    private LocalDateTime bloqueadoEm;

    @NotBlank
    private String sistemaResponsavel;

    @NotNull
    private Boolean ativo;

    @Deprecated
    public Bloqueio(){}

    public Bloqueio(String id, @NotNull LocalDateTime bloqueadoEm, @NotBlank String sistemaResponsavel, @NotNull Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(BloqueioResponse bloqueioResponse){
        this.id = bloqueioResponse.getId();
        this.bloqueadoEm = this.getBloqueadoEm();
        this.sistemaResponsavel = this.getSistemaResponsavel();
        this.ativo = this.getAtivo();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}