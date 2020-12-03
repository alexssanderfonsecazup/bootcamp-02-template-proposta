package br.com.bootcamp.zup.proposta.compartilhado.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64,Object> {
    @Override
    public void initialize(IsBase64 constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        try{
            Base64.getDecoder().decode((String) value);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
}
