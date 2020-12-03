package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;



import br.com.bootcamp.zup.proposta.cartao.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponse {

    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @Valid
    @NotNull
    private List<BloqueioResponse> bloqueios;

    @Valid
    @NotNull
    private List<AvisoViagemResponse> avisos;


    @Valid
    @NotNull
    private List<CarteiraDigitalResponse> carteiras;

    @Valid
    @NotNull
    private List<ParcelaResponse> parcelas;


    @NotNull
    private Integer limite;

    @Valid
    private RenegociacaoResponse renegociacao;

    @Valid
    @NotNull
    private VencimentoResponse vencimento;

    @NotBlank
    private String idProposta;

    public Cartao toModel() {
        List<Bloqueio> bloqueios = BloqueioResponse.toModelList(this.bloqueios);
        List<CarteiraDigital> carteiras = CarteiraDigitalResponse.toModelList(this.carteiras);
        List<Parcela> parcelas = ParcelaResponse.toModelList(this.parcelas);
        List<AvisoViagem> avisos = AvisoViagemResponse.toModelList(this.avisos);
        return new Cartao(id, emitidoEm, titular, bloqueios, carteiras, parcelas, avisos, limite);
    }


    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<BloqueioResponse> getBloqueios() {
        return bloqueios;
    }

    public List<CarteiraDigitalResponse> getCarteiras() {
        return carteiras;
    }

    public List<ParcelaResponse> getParcelas() {
        return parcelas;
    }

    public List<AvisoViagemResponse> getAvisos() {
        return avisos;
    }

    public Integer getLimite() {
        return limite;
    }

    public RenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }


}
