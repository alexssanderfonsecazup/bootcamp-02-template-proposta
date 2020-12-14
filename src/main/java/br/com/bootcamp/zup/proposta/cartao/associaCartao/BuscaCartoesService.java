package br.com.bootcamp.zup.proposta.cartao.associaCartao;

import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.CartaoClientResponse;
import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.proposta.Proposta;
import br.com.bootcamp.zup.proposta.proposta.PropostaRepository;
import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BuscaCartoesService {

    private final Logger logger = LoggerFactory.getLogger(AssociaNumeroCartaoSchedule.class);

    @Autowired
    private CartaoClient cartaoClient;


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate txTemplate;


    @Autowired
    private PropostaRepository propostaRepository;


    @SuppressWarnings("ConstantConditions")
    public void associaCartao() {

        boolean pendentes = true;

        while (pendentes) {

            pendentes = txTemplate.execute((transactionStatus) -> {
                List<Proposta> propostas = propostaRepository.findFirst10ByStatusIs(StatusEnum.ELEGIVEL_SEM_CARTAO);
                if (propostas.isEmpty()) {
                    return false;
                }
                propostas.forEach(this::tentaAssociarCartao);
                return true;
            });

        }
    }

    private void tentaAssociarCartao(Proposta proposta) {
        try {
            CartaoClientResponse cartaoClientResponse = cartaoClient.verificaSeCartaoFoiCriado(proposta.getId().toString());

            Cartao cartao = cartaoClientResponse.toModel();
            entityManager.persist(cartao);

            proposta.setCartao(cartao);
            proposta.setStatus(StatusEnum.ELEGIVEL_COM_CARTAO);

            logger.info("Associado o cartão {} a proposta {}", cartao.getId(), proposta.getId());
        } catch (FeignException e) {
            logger.info("Não foi encontrado um cartão disponível para a proposta {}", proposta.getId());
        }
    }
}