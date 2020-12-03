package br.com.bootcamp.zup.proposta.proposta.nova;

import br.com.bootcamp.zup.proposta.compartilhado.client.SolicitacaoClient;
import br.com.bootcamp.zup.proposta.compartilhado.util.ExecutorTransacao;
import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.cartao.associaCartao.request.CriaCartaoRequest;
import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import br.com.bootcamp.zup.proposta.proposta.nova.request.NovaPropostaRequest;
import br.com.bootcamp.zup.proposta.proposta.nova.request.SolicitaoPropostaRequest;
import br.com.bootcamp.zup.proposta.proposta.nova.response.SolicitacaoPropostaResponse;
import br.com.bootcamp.zup.proposta.proposta.nova.validator.DocumentoUnicoValidator;
import br.com.bootcamp.zup.proposta.proposta.Proposta;
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
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
        Proposta proposta = request.toModel();
        executorTransacao.salvaEComita(proposta);
        URI uri = uriBuilder.path("/novaProposta/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
        try {
            SolicitacaoPropostaResponse responseSolicitacao = analiseClient.consulta(new SolicitaoPropostaRequest(proposta));
            proposta.setStatus(StatusEnum.valueOfLabel(responseSolicitacao.getResultadoSolicitacao()));
            executorTransacao.atualizaEComita(proposta);
            logger.info("Proposta documento={} salário={} criada com sucesso !", proposta.getDocumento(), proposta.getSalario());

            cartaoClient.solicitaCriacaoDoCartao(new CriaCartaoRequest(proposta));

        } catch (FeignException.UnprocessableEntity ex) {
            proposta.setStatus(StatusEnum.NAO_ELEGIVEL);
            logger.info("Proposta documento={} salário={} criada porém cliente não elegivel!", proposta.getDocumento(), proposta.getSalario());
        }catch(FeignException e){
            logger.error(e.getLocalizedMessage());
        }
        return ResponseEntity.created(uri).build();
    }
}
