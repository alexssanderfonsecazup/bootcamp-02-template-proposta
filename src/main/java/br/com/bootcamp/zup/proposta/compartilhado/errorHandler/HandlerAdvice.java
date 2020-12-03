package br.com.bootcamp.zup.proposta.compartilhado.errorHandler;

import br.com.bootcamp.zup.proposta.compartilhado.exception.ApiErroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErroFormulario handleErroDeValidacao(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(fieldError -> String.format("Campo %s %s", fieldError.getField(), messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());

        return new ErroFormulario(errors);

    }


    //Utilize o padrão nomeDoCampo_unico nas contraints unicas para que o handler
    //consiga recuperar o nome do campo
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleErroDeCampoUnico(DataIntegrityViolationException e) {

        String causaCompleta = e.getMostSpecificCause().getMessage();
        Pattern pattern = Pattern.compile("(\\w+)_unico", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(causaCompleta);

        if (matcher.find()) {
            String campo = matcher.group(1);
            return String.format("O $ já foi cadastrado",campo);
        }
        return "Violação de integridade";

    }

    @ExceptionHandler(ApiErroException.class)
    public ResponseEntity<ErroFormulario> handleApiErroException(ApiErroException apiErroException) {

        ErroFormulario erro = new ErroFormulario(Arrays.asList(apiErroException.getMessage()));
        return ResponseEntity.status(apiErroException.getHttpStatus()).body(erro);
    }


}
