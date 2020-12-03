package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;

import br.com.bootcamp.zup.proposta.cartao.model.CarteiraDigital;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CarteiraDigitalResponse {

    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

    public static List<CarteiraDigital> toModelList(List<CarteiraDigitalResponse> carteiras){
        return carteiras.stream().map(CarteiraDigital::new)
                .collect(Collectors.toList());
    }
}
