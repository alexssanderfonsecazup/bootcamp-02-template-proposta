package br.com.bootcamp.zup.proposta.cartao.associaCartao.request;

import br.com.bootcamp.zup.proposta.proposta.Proposta;

public class CriaCartaoClientRequest {

    private String nome;
    private String documento;
    private String idProposta;


    public CriaCartaoClientRequest(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getId().toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
