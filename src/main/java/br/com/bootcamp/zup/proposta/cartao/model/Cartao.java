package br.com.bootcamp.zup.proposta.cartao.model;

import br.com.bootcamp.zup.proposta.cartao.biometria.Biometria;
import br.com.bootcamp.zup.proposta.cartao.model.AvisoViagem;
import br.com.bootcamp.zup.proposta.cartao.model.Bloqueio;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
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
    @NotNull
    @OneToMany
    private List<Bloqueio> bloqueios;

    @Valid
    @NotNull
    @OneToMany
    private List<CarteiraDigital> carteiras;

    @Valid
    @NotNull
    @OneToMany
    private List<Parcela> parcelas;

    @Valid
    @NotNull
    @OneToMany
    private List<AvisoViagem> avisos;

    @NotNull
    private Integer limite;

    @OneToMany
    private Set<Biometria> biometrias;

    @Deprecated
    public Cartao(){}

    public Cartao(@NotBlank String id, @NotNull LocalDateTime emitidoEm,
                  @NotBlank String titular, @Valid @NotNull List<Bloqueio> bloqueios,
                  @Valid @NotNull List<CarteiraDigital> carteiras, @Valid @NotNull List<Parcela> parcelas,
                  @Valid @NotNull List<AvisoViagem> avisos, @NotNull Integer limite) {
        this.numero = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.avisos = avisos;
        this.limite = limite;
    }

    public UUID getId() {
        return id;
    }

    public void setBiometria(Set<Biometria> biometrias) {
        this.biometrias = biometrias;
    }

    public Set<Biometria> getBiometrias() {
        return biometrias;
    }

    public String getNumero() {
        return numero;
    }
}
