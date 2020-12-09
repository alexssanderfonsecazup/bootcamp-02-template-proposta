package br.com.bootcamp.zup.proposta.cartao.associaCartao;

import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.CartaoClientResponse;
import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.proposta.Proposta;
import br.com.bootcamp.zup.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BuscaCartoesService {

    private final Logger logger = LoggerFactory.getLogger(AssociaNumeroCartaoSchedule.class);

    @Autowired
    CartaoClient cartaoClient;


    @Autowired
    EntityManager entityManager;


    @Autowired
    private PropostaRepository propostaRepository;


    @Transactional
    public void retornaListaDePropostasSemCartaoAssociado() {
         associaCartao(propostaRepository.findFirst1000ByCartaoIsNull());
    }

    private void associaCartao(List<Proposta> propostas) {

        propostas.forEach(proposta -> {
            try {
                CartaoClientResponse cartaoClientResponse = cartaoClient.verificaSeCartaoFoiCriado(proposta.getId().toString());
                Cartao cartao = cartaoClientResponse.toModel();
                entityManager.persist(cartao);

                proposta.setCartao(cartao);
                logger.info("Associado o cartão {} a proposta {}", cartao.getId(), proposta.getId());
            } catch (FeignException e) {
                logger.info("Não foi encontrado um cartão disponível para a proposta {}", proposta.getId());
            }
        });

    }
}