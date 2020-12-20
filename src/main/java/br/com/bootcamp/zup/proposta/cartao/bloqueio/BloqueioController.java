package br.com.bootcamp.zup.proposta.cartao.bloqueio;

import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.compartilhado.client.CartaoClient;
import br.com.bootcamp.zup.proposta.compartilhado.exception.ApiErroException;
import br.com.bootcamp.zup.proposta.compartilhado.util.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/cartao/{id}/bloqueio")
public class BloqueioController {


    @Autowired
    CartaoClient cartaoClient;

    @Autowired
    ExecutorTransacao executorTransacao;

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(BloqueioController.class);


    @PostMapping
    public ResponseEntity<?> bloquearCartao(@PathVariable UUID id, @RequestHeader(value = HttpHeaders.USER_AGENT) String userAgent, HttpServletRequest request) {

        Cartao cartao = entityManager.find(Cartao.class, id);
        if (cartao == null) {
            return ResponseEntity.notFound().build();
        }
        if (cartao.temBloqueio()) {
            throw new ApiErroException("Cartão já está bloqueado", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Bloqueio bloqueio = new Bloqueio(request.getLocalAddr(), cartao, userAgent);
        executorTransacao.salvaEComita(bloqueio);

        try {
            Map<String,String> map = Map.of("sistemaResponsavel", userAgent);
            cartaoClient.bloquearCartao(cartao.getNumero(), map);
            logger.info("Cartao {} foi bloqueado com sucesso", cartao.getId());

        } catch (FeignException ex) {
            logger.error("Erro ao bloquear cartão no sistema legado {}" +ex.contentUTF8());
        }

        return ResponseEntity.ok().build();
    }
}
