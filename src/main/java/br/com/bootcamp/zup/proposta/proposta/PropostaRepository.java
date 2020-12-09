package br.com.bootcamp.zup.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    List<Proposta> findFirst1000ByCartaoIsNull();
}
