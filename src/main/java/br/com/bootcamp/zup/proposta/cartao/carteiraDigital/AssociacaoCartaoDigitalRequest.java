package br.com.bootcamp.zup.proposta.cartao.carteiraDigital;

import br.com.bootcamp.zup.proposta.cartao.Cartao;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AssociacaoCartaoDigitalRequest {

    @NotBlank
    @Email
    private String email;

    public CarteiraDigital toModel(String carteira, Cartao cartao){
        return new CarteiraDigital(email,carteira, cartao);
    }

    public String getEmail() {
        return email;
    }
}
