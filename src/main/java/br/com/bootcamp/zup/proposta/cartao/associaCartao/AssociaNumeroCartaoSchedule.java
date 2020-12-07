package br.com.bootcamp.zup.proposta.cartao.associaCartao;

import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.CartaoClientResponse;
import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.compartilhado.util.ExecutorTransacao;
import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.proposta.Proposta;
import br.com.bootcamp.zup.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociaNumeroCartaoSchedule {

    private final Logger logger = LoggerFactory.getLogger(AssociaNumeroCartaoSchedule.class);


    @Autowired
    CartaoClient cartaoClient;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    ExecutorTransacao executorTransacao;


    @Scheduled(fixedDelayString = "${periodicidade.busca-cartao}")
    protected void associaCartaoAProposta() {
        List<Proposta> propostasSemCartao = propostaRepository.findByNumeroCartaoIsNull();
        if (!propostasSemCartao.isEmpty()) {
            propostasSemCartao.forEach(proposta -> {
                buscaESalvaCartao(proposta);
            });
        }
    }

    private void buscaESalvaCartao(Proposta proposta) {
        try {
            CartaoClientResponse cartaoClientResponse = cartaoClient.verificaSeCartaoFoiCriado(proposta.getId().toString());
            Cartao cartao = cartaoClientResponse.toModel();
            executorTransacao.salvaEComita(cartao);

            proposta.setNumeroCartao(cartao.getNumero());
            executorTransacao.atualizaEComita(proposta);

            logger.info("Associado o cartão {} para a proposta {}", cartao.getId(), proposta.getId());

        } catch (FeignException e) {
            logger.info("Não foi encontrado um cartão disponível para a proposta {}", proposta.getId());
        }
    }


}
