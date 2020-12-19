package br.com.bootcamp.zup.proposta.proposta.consulta.response;

import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConsultaPropostaResponse {

    private UUID id;
    private String nome;
    private StatusEnum status;
<<<<<<< HEAD
    private String numero;
=======
>>>>>>> parent of 4f4f45c (Refatoracao job associacao e remocao imports nao usados)


    public ConsultaPropostaResponse(UUID id, String nome, StatusEnum status) {
        this.id = id;
        this.nome = nome;
        this.status = status;

    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public StatusEnum getStatus() {
        return status;
    }


}
