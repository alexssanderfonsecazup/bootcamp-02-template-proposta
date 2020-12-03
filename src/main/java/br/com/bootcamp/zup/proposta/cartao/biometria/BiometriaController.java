package br.com.bootcamp.zup.proposta.cartao.biometria;

import br.com.bootcamp.zup.proposta.cartao.model.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("cartoes/biometria")
public class BiometriaController {

    @PersistenceContext
    EntityManager entityManager;

    private final Logger logger =  LoggerFactory.getLogger(BiometriaController.class);


    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastraBiometria(@PathVariable String id, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder uriBuilder) {
        Cartao cartao = entityManager.find(Cartao.class, UUID.fromString(id));
        if (cartao == null) {
         return ResponseEntity.notFound().build();
        }

        Biometria biometria = biometriaRequest.toModel();
        entityManager.persist(biometria);

        cartao.getBiometrias().add(biometria);
        URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId().toString()).toUri();

        logger.info("Biometria {} criada com sucesso",biometria.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornaBiometria(@PathVariable String id){

        Biometria biometria = entityManager.find(Biometria.class, UUID.fromString(id));
        if (biometria == null) {
            return ResponseEntity.notFound().build();
        }
        BiometriaResponse biometriaResponse = new BiometriaResponse(biometria);
        return ResponseEntity.ok(biometriaResponse);

    }

}
