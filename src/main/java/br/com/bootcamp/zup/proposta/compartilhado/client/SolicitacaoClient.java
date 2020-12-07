package br.com.bootcamp.zup.proposta.compartilhado.client;


import br.com.bootcamp.zup.proposta.proposta.nova.request.SolicitaoPropostaClientRequest;
import br.com.bootcamp.zup.proposta.proposta.nova.response.SolicitacaoPropostaResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${solicitacoes.host}",name = "solicitacao")
@Component
public interface SolicitacaoClient {

    @PostMapping("/")
    SolicitacaoPropostaResponseClient consulta(SolicitaoPropostaClientRequest request);

}
