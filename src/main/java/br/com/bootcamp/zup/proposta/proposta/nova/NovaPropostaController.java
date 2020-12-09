package br.com.bootcamp.zup.proposta.proposta.nova;

import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.compartilhado.client.SolicitacaoClient;
import br.com.bootcamp.zup.proposta.compartilhado.util.ExecutorTransacao;
import br.com.bootcamp.zup.proposta.proposta.Proposta;
import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import br.com.bootcamp.zup.proposta.proposta.nova.request.NovaPropostaRequest;
import br.com.bootcamp.zup.proposta.proposta.nova.request.SolicitaoPropostaClientRequest;
import br.com.bootcamp.zup.proposta.proposta.nova.validator.DocumentoUnicoValidator;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("propostas")
public class NovaPropostaController {


    @Autowired
    DocumentoUnicoValidator documentoUnicoValidator;

    @Autowired
    SolicitacaoClient analiseClient;

    @Autowired
    CartaoClient cartaoClient;

    @Autowired
    ExecutorTransacao executorTransacao;

    private final Logger logger = LoggerFactory.getLogger(NovaPropostaController.class);


    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(documentoUnicoValidator);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest request,
                                       UriComponentsBuilder uriBuilder) {
        Proposta proposta = request.toModel();
        executorTransacao.salvaEComita(proposta);
        URI uri = uriBuilder.path("/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
        try {
            Map<String,String> responseSolicitacao = analiseClient.consulta(new SolicitaoPropostaClientRequest(proposta));
            proposta.setStatus(StatusEnum.valueOfLabel(responseSolicitacao.get("resultadoSolicitacao")));
            executorTransacao.atualizaEComita(proposta);
            logger.info("Proposta ={} criada com sucesso para o cliente {} !", proposta.getId(), proposta.getNome());

        } catch (FeignException.UnprocessableEntity ex) {
            proposta.setStatus(StatusEnum.NAO_ELEGIVEL);
            executorTransacao.atualizaEComita(proposta);
            logger.info("Proposta {} criada porém {} não é elegivel para o cartão!", proposta.getId(), proposta.getSalario());

        }
        return ResponseEntity.created(uri).build();

    }
}
