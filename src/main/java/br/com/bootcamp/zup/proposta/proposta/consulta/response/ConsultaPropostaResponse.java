package br.com.bootcamp.zup.proposta.proposta.consulta.response;

import br.com.bootcamp.zup.proposta.proposta.Proposta;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConsultaPropostaResponse {

    private String id;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private String documento;
    private String status;
    private String numeroCartao;

    public ConsultaPropostaResponse(Proposta proposta) {
        this.id = proposta.getId().toString();
        this.nome = proposta.getNome();;
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus().toString();
        this.numeroCartao = proposta.getNumeroCartao();
        this.documento = proposta.getDocumento();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getDocumento() {
        return documento;
    }

    public String getStatus() {
        return status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
