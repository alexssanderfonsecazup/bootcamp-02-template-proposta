package br.com.bootcamp.zup.proposta.cartao.viagem;

import br.com.bootcamp.zup.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Viagem {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String destino;


    @NotNull
    private Instant instanteAviso = Instant.now();

    @NotBlank
    private String ipCliente;
    ;

    @NotBlank
    private String userAgent;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @FutureOrPresent
    private LocalDate validoAte;

    @ManyToOne
    @NotNull
    private Cartao cartao;


    @Deprecated
    public Viagem() {
    }

    public Viagem(@NotBlank String destino, @NotBlank String ipCliente, @NotBlank String userAgent,
                  @NotNull @FutureOrPresent LocalDate validoAte, @NotNull Cartao cartao) {
        this.destino = destino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.validoAte = validoAte;
        this.cartao = cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
