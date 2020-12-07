package br.com.bootcamp.zup.proposta.cartao.carteiraDigital;

import br.com.bootcamp.zup.proposta.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String carteira;

    @ManyToOne
    @NotNull
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital(){}


    public CarteiraDigital(@NotBlank @Email String email, @NotBlank String carteira, @NotNull Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraDigital that = (CarteiraDigital) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(carteira, that.carteira);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, carteira);
    }
}
