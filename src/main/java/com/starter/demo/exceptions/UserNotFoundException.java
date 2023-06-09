package com.starter.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 5447585512311811320L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
