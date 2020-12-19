package br.com.bootcamp.zup.proposta.proposta;


import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PropostaTest {

    @Test
    @DisplayName("Verifica se o status está sendo atríbuido corretamento para SEM_RESTRICAO")
    public void testaSetStatusEligivel(){
        Proposta proposta = new Proposta("Alex","Rua Otavio de Brito",new BigDecimal(5000.0),"13503143661");
        proposta.defineStatus("SEM_RESTRICAO");
        Assertions.assertEquals(StatusEnum.ELEGIVEL_SEM_CARTAO,proposta.getStatus());
    }

    @Test
    @DisplayName("Verifica se o status está sendo atríbuido corretamento para COM_RESTRICAO")
    public void testaSetStatusNaoEligivel(){
        Proposta proposta = new Proposta("Alex","Rua Otavio de Brito",new BigDecimal(5000.0),"13503143661");
        proposta.defineStatus("COM_RESTRICAO");
        Assertions.assertEquals(StatusEnum.NAO_ELEGIVEL,proposta.getStatus());
    }
}
