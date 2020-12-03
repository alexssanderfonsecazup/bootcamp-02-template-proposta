package br.com.bootcamp.zup.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PropostaRepository extends JpaRepository<Proposta, UUID> {
    List<Proposta> findByNumeroCartaoIsNull();
}