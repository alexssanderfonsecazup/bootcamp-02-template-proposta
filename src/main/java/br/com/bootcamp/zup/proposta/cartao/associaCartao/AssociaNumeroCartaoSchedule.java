package br.com.bootcamp.zup.proposta.cartao.associaCartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AssociaNumeroCartaoSchedule {

    @Autowired
    BuscaCartoesService buscaCartoesComponent;

    @Scheduled(fixedDelayString = "${periodicidade.busca-cartao}")
    protected void associaCartaoAProposta() {
        buscaCartoesComponent.retornaListaDePropostasSemCartaoAssociado();
    }



}
