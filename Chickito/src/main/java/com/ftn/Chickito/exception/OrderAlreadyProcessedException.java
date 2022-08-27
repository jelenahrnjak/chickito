package com.ftn.Chickito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderAlreadyProcessedException extends RuntimeException{

    public OrderAlreadyProcessedException(Long id) {
        super(String.format("Order with id = %s already processed.", id) );
    }
}