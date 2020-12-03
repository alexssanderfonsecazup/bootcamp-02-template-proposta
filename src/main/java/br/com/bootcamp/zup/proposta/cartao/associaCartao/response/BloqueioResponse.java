package br.com.bootcamp.zup.proposta.cartao.associaCartao.response;

import br.com.bootcamp.zup.proposta.cartao.model.Bloqueio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BloqueioResponse {

    @NotBlank
    private String id;
    @NotNull
    private LocalDateTime bloqueadoEm;

    @NotBlank
    private String sistemaResponsavel;

    @NotNull
    private Boolean ativo;

    public Bloqueio toModel(){
        return new Bloqueio(id,bloqueadoEm,sistemaResponsavel,ativo);
    }

    public static List<Bloqueio> toModelList(List<BloqueioResponse> bloqueios){
        return bloqueios.stream().map(Bloqueio::new)
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public void setBloqueadoEm(LocalDateTime bloqueadoEm) {
        this.bloqueadoEm = bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
