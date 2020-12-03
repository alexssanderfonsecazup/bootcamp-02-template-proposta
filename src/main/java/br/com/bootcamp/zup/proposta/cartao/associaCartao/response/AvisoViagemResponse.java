package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;

import br.com.bootcamp.zup.proposta.cartao.model.AvisoViagem;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AvisoViagemResponse {

    @NotBlank
    private LocalDate validoAte;
    @NotBlank
    private String destino;

    public static List<AvisoViagem> toModelList(List<AvisoViagemResponse> avisos) {
        return avisos.stream()
                .map(AvisoViagem::new)
                .collect(Collectors.toList());
    }


    public AvisoViagem toModel(){
        return new AvisoViagem(validoAte,destino);
    }


    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
