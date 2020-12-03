package br.com.bootcamp.zup.proposta.cartao.biometria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometria {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull @NotBlank
    private String fingerprint;

    private final LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    public Biometria(){}

    public Biometria(@NotNull @NotBlank String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public UUID getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
