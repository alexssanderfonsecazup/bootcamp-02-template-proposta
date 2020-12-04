package br.com.bootcamp.zup.proposta.cartao;

import br.com.bootcamp.zup.proposta.cartao.biometria.Biometria;
import br.com.bootcamp.zup.proposta.cartao.bloqueio.Bloqueio;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Cartao {

    @Id
    @GeneratedValue
    private UUID id;

    @NaturalId
    @NotBlank
    private String numero;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @Valid
    @OneToOne(mappedBy = "cartao")
    private Bloqueio bloqueio;

    @NotNull
    private Integer limite;

    @OneToMany
    private Set<Biometria> biometrias;



    @Deprecated
    public Cartao(){}


    public Cartao(@NotBlank String numero, @NotNull LocalDateTime emitidoEm, @NotBlank String titular, @NotNull Integer limite) {
        this.numero = numero;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;

    }


    public boolean temBloqueio() {
        return bloqueio !=null;
    }

    public UUID getId() {
        return id;
    }

    public Set<Biometria> getBiometrias() {
        return biometrias;
    }

    public String getNumero() {
        return numero;
    }
    public void setBiometria(Set<Biometria> biometrias) {
        this.biometrias = biometrias;
    }

    public void setBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
    }

}
