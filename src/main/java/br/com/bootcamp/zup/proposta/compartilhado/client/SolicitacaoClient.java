package br.com.bootcamp.zup.proposta.compartilhado.client;


import br.com.bootcamp.zup.proposta.proposta.nova.request.SolicitaoPropostaRequest;
import br.com.bootcamp.zup.proposta.proposta.nova.response.SolicitacaoPropostaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${solicitacao.host}",name = "solicitacao")
@Component
public interface SolicitacaoClient {

    @PostMapping
    SolicitacaoPropostaResponse consulta(SolicitaoPropostaRequest request);

}
