package br.com.bootcamp.zup.proposta.proposta.nova.request;

import br.com.bootcamp.zup.proposta.proposta.Proposta;

public class SolicitaoPropostaRequest {

    private String idProposta;
    private String documento;
    private String nome;

    public SolicitaoPropostaRequest(Proposta proposta){
        this.idProposta = proposta.getId().toString();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
    }


    public String getDocumento() {
        return documento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }

}
