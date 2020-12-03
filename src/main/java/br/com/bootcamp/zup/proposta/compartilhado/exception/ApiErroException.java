package br.com.bootcamp.zup.proposta.compartilhado.exception;

import org.springframework.http.HttpStatus;

public class ApiErroException extends RuntimeException {

    private final HttpStatus  httpStatus;
    private final String reason;

    public ApiErroException(String reason, HttpStatus httpStatus) {
        super(reason);
        this.reason = reason;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
