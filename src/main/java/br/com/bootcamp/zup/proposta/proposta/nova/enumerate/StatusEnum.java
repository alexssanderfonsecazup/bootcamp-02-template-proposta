package br.com.bootcamp.zup.proposta.proposta.nova.enumerate;

public enum StatusEnum {

    NAO_ELEGIVEL("COM_RESTRICAO"),
    ELEGIVEL_SEM_CARTAO("ELEGIVEL_SEM_CARTAO"),
    ELEGIVEL_COM_CARTAO("ELEGIVEL COM CARTAO"),
    ELEGIVEL("SEM_RESTRICAO");

    public String status;

    StatusEnum(String status){
    this.status = status;
    }


}
