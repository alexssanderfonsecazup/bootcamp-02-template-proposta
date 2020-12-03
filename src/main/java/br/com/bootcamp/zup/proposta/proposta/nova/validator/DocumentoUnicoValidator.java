package br.com.bootcamp.zup.proposta.proposta.nova.validator;

import br.com.bootcamp.zup.proposta.compartilhado.exception.ApiErroException;
import br.com.bootcamp.zup.proposta.proposta.nova.request.NovaPropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Component
public class DocumentoUnicoValidator implements Validator {

    @Autowired
    EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaPropostaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        NovaPropostaRequest request = (NovaPropostaRequest) target;
        Query query = entityManager.createQuery("SELECT 1 FROM Proposta p WHERE p.documento = '"+request.getDocumento()+"'");
        if(query.getResultList().size()>=1){
            throw new ApiErroException("Documento já existe",HttpStatus.UNPROCESSABLE_ENTITY);
        }


    }
}
