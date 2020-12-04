package br.com.bootcamp.zup.proposta.proposta.consulta;

import br.com.bootcamp.zup.proposta.proposta.consulta.response.ConsultaPropostaResponse;
import br.com.bootcamp.zup.proposta.proposta.Proposta;
import br.com.bootcamp.zup.proposta.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/propostas")
public class ConsultaPropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> consulta(@PathVariable UUID id){
        Optional<Proposta> propostaOptional = propostaRepository.findById(id);
        propostaOptional.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada"));
        ConsultaPropostaResponse response = new ConsultaPropostaResponse(propostaOptional.get());
        return ResponseEntity.ok(response);

    }
}
