package br.com.bootcamp.zup.proposta.cartao.viagem.request;

import br.com.bootcamp.zup.proposta.cartao.viagem.Viagem;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ViagemRequestClient {

    @NotBlank
    private String destino;
    @PastOrPresent @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    public ViagemRequestClient(@NotBlank String destino, @PastOrPresent @NotNull LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public ViagemRequestClient(Viagem viagem) {
        this.destino = viagem.getDestino();
        this.validoAte = viagem.getValidoAte();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
