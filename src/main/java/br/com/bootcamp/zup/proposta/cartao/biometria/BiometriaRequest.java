package br.com.bootcamp.zup.proposta.cartao.biometria;

import br.com.bootcamp.zup.proposta.compartilhado.annotation.IsBase64;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BiometriaRequest {

    @NotNull
    @NotBlank
    @IsBase64
    private String fingerprint;


    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria toModel() {
        return new Biometria(fingerprint);
    }
}
