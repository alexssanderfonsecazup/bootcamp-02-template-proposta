package br.com.bootcamp.zup.proposta.proposta.consulta;

import br.com.bootcamp.zup.proposta.proposta.Proposta;
import br.com.bootcamp.zup.proposta.proposta.PropostaRepository;
import br.com.bootcamp.zup.proposta.proposta.consulta.response.ConsultaPropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@RestController
@RequestMapping("/propostas")
public class ConsultaPropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity<?> consulta(@PathVariable UUID id) {


        try {
            ConsultaPropostaResponse consulta = entityManager.createQuery("SELECT new  br.com.bootcamp.zup.proposta.proposta.consulta.response.ConsultaPropostaResponse" +
                    "(p.id, p.nome, p.status) FROM Proposta p WHERE p.id = :pId", ConsultaPropostaResponse.class).setParameter("pId", id)
                    .getSingleResult();

            return ResponseEntity.ok(consulta);
        } catch (NoResultException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
        }
    }
}
