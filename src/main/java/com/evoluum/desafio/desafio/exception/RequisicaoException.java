package com.evoluum.desafio.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequisicaoException extends RuntimeException {
    public RequisicaoException(String message) {
        super(message);
    }
}
