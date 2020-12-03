package br.com.bootcamp.zup.proposta.compartilhado.annotation;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj,Object>  {

    @Override
    public void initialize(CpfOrCnpj params) { }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid((CharSequence) value, null) || cnpjValidator.isValid((CharSequence) value, null);

    }
}
