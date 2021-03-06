package br.com.bootcamp.zup.proposta.proposta.nova.request;

import br.com.bootcamp.zup.proposta.proposta.Proposta;

public class SolicitaoPropostaClientRequest {

    private final String idProposta;
    private final String documento;
    private final String nome;

    public SolicitaoPropostaClientRequest(Proposta proposta){
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
