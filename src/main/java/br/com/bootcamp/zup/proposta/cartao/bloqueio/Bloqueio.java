package br.com.bootcamp.zup.proposta.cartao.bloqueio;

import br.com.bootcamp.zup.proposta.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
public class Bloqueio {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private LocalDateTime bloqueadoEm = LocalDateTime.now();

    @NotBlank
    private String ip;

    @NotBlank
    private String userAgent;

    @OneToOne
    @NotNull
    private Cartao cartao;

    @Deprecated
    public Bloqueio(){}


    public Bloqueio(@NotBlank String ip, @NotNull Cartao cartao,
                    @NotBlank String userAgent) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;

    }

}