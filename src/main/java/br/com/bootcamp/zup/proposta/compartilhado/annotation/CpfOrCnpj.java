package br.com.bootcamp.zup.proposta.compartilhado.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CpfOrCnpjValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface CpfOrCnpj {

    String message() default "O documento deve ser um cpf ou cnpj";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default{};

}
