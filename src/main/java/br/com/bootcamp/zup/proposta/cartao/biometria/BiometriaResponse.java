package br.com.bootcamp.zup.proposta.cartao.biometria;

import java.time.LocalDateTime;

public class BiometriaResponse {
    private String id;
    private LocalDateTime dataCriacao;
    private String fingerprint;

    public BiometriaResponse(Biometria biometria) {
        this.id = biometria.getId().toString();
        this.dataCriacao = biometria.getDataCriacao();
        this.fingerprint = biometria.getFingerprint();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
