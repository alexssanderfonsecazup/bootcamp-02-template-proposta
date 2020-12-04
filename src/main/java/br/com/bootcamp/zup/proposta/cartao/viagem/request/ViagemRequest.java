package br.com.bootcamp.zup.proposta.cartao.viagem.request;

import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.cartao.viagem.Viagem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ViagemRequest {

    @NotBlank
    private String destino;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate validoAte;


    public Viagem toModel(String ipCliente, String userAgent, Cartao cartao){
        return new Viagem(destino,ipCliente,userAgent,validoAte,cartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return validoAte;
    }
}
