package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;


import br.com.bootcamp.zup.proposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CartaoClientResponse {

    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;


    @NotNull
    private Integer limite;


    @NotBlank
    private String idProposta;

    public Cartao toModel() {
        return new Cartao(id,emitidoEm,titular,limite);
    }


    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Integer getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
