package br.com.bootcamp.zup.proposta.cartao.model;

import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.AvisoViagemResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private LocalDate validoAte;
    @NotBlank
    private String destino;

    @Deprecated
    public AvisoViagem(){}

    public AvisoViagem(@NotBlank LocalDate validoAte, @NotBlank String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagem(AvisoViagemResponse avisoViagemResponse){
        this.validoAte = avisoViagemResponse.getValidoAte();
        this.destino = avisoViagemResponse.getDestino();
    }
}
