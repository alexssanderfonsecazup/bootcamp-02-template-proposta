package br.com.bootcamp.zup.proposta.cartao.carteiraDigital;


import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.compartilhado.exception.ApiErroException;
import br.com.bootcamp.zup.proposta.compartilhado.util.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes/{idCartao}/carteiras")
public class CarteiraDigitalController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CartaoClient cartaoClient;

    @Autowired
    ExecutorTransacao executorTransacao;

    private final Logger logger = LoggerFactory.getLogger(CarteiraDigitalController.class);

    @PostMapping("/paypal")
    public ResponseEntity<?> associaCartaoComPaypal(@PathVariable UUID idCartao,
                                                    @RequestBody @Valid AssociacaoCartaoDigitalRequest associacaoCartaoDigitalRequest,
                                                    UriComponentsBuilder uriBuilder) {
        Cartao cartao = entityManager.find(Cartao.class, idCartao);
        if (cartao == null) {
            return ResponseEntity.notFound().build();
        }
        CarteiraDigital carteiraDigital = associacaoCartaoDigitalRequest.toModel("Paypal",cartao);

        if (cartao.verificaAssociacaoCarteira(carteiraDigital)) {
            throw new ApiErroException("Está carteira paypal ja foi associada a este cartão", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        try {
            AssociacaoCarteiraDigitalResponseCliente associacaoResponse = cartaoClient.associaCarteiraDigital(cartao.getNumero(),
                    new AssociaCarteiraDigitalRequestClient(carteiraDigital.getEmail(), carteiraDigital.getCarteira()));

            executorTransacao.salvaEComita(carteiraDigital);
            logger.info("Carteira digital paypal associada com sucesso ao cartao : {} ", cartao.getId());

            URI uri = uriBuilder.path("/cartoes/{idCartao}/carteiras/{idCarteira}").buildAndExpand(cartao.getNumero(), carteiraDigital.getId()).toUri();
            return ResponseEntity.created(uri).build();

        } catch (FeignException ex) {
            logger.error("Falha ao associar a carteira paypal com o cartao: {}, status: {}, content: {}", cartao.getId(), ex.status(), ex.contentUTF8());
        }
        return ResponseEntity.ok().build();

    }


    @PostMapping("/samsung")
    public ResponseEntity<?> associaCartaoComSamsung(@PathVariable UUID idCartao,
                                                    @RequestBody @Valid AssociacaoCartaoDigitalRequest associacaoCartaoDigitalRequest,
                                                    UriComponentsBuilder uriBuilder) {
        Cartao cartao = entityManager.find(Cartao.class, idCartao);
        if (cartao == null) {
            return ResponseEntity.notFound().build();
        }
        CarteiraDigital carteiraDigital = associacaoCartaoDigitalRequest.toModel("Samsung",cartao);

        if (cartao.verificaAssociacaoCarteira(carteiraDigital)) {
            throw new ApiErroException("Está carteira samsung já foi associada a este cartão", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            AssociacaoCarteiraDigitalResponseCliente associacaoResponse = cartaoClient.associaCarteiraDigital(cartao.getNumero(),
                    new AssociaCarteiraDigitalRequestClient(carteiraDigital.getEmail(), carteiraDigital.getCarteira()));

            executorTransacao.salvaEComita(carteiraDigital);
            logger.info("Carteira digital samsung associada com sucesso ao cartao : {} ", cartao.getId());

            URI uri = uriBuilder.path("/cartoes/{idCartao}/carteiras/{idCarteira}").buildAndExpand(cartao.getNumero(), carteiraDigital.getId()).toUri();
            return ResponseEntity.created(uri).build();

        } catch (FeignException ex) {
            logger.error("Falha ao associar a carteira samsung com o cartao: {}, status: {}, content: {}", cartao.getId(), ex.status(), ex.contentUTF8());
        }
        return ResponseEntity.ok().build();

    }


}
