package br.com.bootcamp.zup.proposta.proposta;

import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.UUID;

public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = (LockOptions.SKIP_LOCKED +""))})
    List<Proposta> findFirst10ByStatusIs(StatusEnum statusEnum);
}
