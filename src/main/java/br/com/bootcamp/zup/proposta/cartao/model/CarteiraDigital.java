package br.com.bootcamp.zup.proposta.cartao.model;

import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.CarteiraDigitalResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class CarteiraDigital {

    @Id
    private String id;

    @NotBlank
    private String email;

    @NotNull
    private LocalDateTime associadoEm;

    @NotBlank
    private String emissor;

    @Deprecated
    public CarteiraDigital(){}

    public CarteiraDigital(@NotBlank String id, @NotBlank String email, @NotNull LocalDateTime associadoEm, @NotBlank String emissor) {
        this.id = id;
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
    }

    public CarteiraDigital(CarteiraDigitalResponse carteiraDigitalResponse){
        this.id = carteiraDigitalResponse.getId();
        this.email = carteiraDigitalResponse.getEmail();
        this.associadoEm = carteiraDigitalResponse.getAssociadaEm();
        this.emissor = carteiraDigitalResponse.getEmissor();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadoEm() {
        return associadoEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
