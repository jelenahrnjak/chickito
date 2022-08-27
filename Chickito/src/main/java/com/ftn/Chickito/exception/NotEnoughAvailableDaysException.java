package com.ftn.Chickito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughAvailableDaysException extends RuntimeException{

    public NotEnoughAvailableDaysException(int availableDays, int requestedDays) {
        super(String.format("You have %d available vacation days, but you requested %d.", availableDays, requestedDays));
    }
}
