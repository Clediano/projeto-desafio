package com.evoluum.desafio.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroInternoException extends RuntimeException {
    public ErroInternoException(String message) {
        super(message);
    }
}
