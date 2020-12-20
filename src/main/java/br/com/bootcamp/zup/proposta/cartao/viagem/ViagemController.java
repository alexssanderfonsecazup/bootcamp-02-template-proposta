package br.com.bootcamp.zup.proposta.cartao.viagem;


import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.cartao.viagem.request.ViagemRequest;
import br.com.bootcamp.zup.proposta.cartao.viagem.request.ViagemRequestClient;
import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.compartilhado.util.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("cartoes/{id}/viagens")
public class ViagemController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    CartaoClient cartaoClient;

    private final Logger logger = LoggerFactory.getLogger(ViagemController.class);

    @PostMapping
    public ResponseEntity<?> avisarViagem(@PathVariable UUID id,
                                          @RequestBody @Valid ViagemRequest viagemRequest,
                                          @RequestHeader(value = HttpHeaders.USER_AGENT) String userAgent,
                                          HttpServletRequest httpServletRequest) {

        Cartao cartao = entityManager.find(Cartao.class, id);
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        String ip = httpServletRequest.getLocalAddr();
        Viagem viagem = viagemRequest.toModel(ip,userAgent,cartao);
        executorTransacao.salvaEComita(viagem);

        try{
            cartaoClient.avisaViagem(cartao.getNumero(),new ViagemRequestClient(viagem));
            logger.info("Aviso de viagem do cartão {} notificado para o sistema legado",cartao.getId());
        }catch(FeignException ex){
            logger.error("Falha ao notificar o aviso de viagem no sistema legado para o cartão: "+cartao.getId());
            logger.error("Status : {}, content : {}",ex.status(), ex.contentUTF8());
        }

        return ResponseEntity.ok().build();

    }
}
