package com.ftn.Chickito.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WrongReviewerException extends RuntimeException{

    public WrongReviewerException() {
        super("Reviewer not allowed to process this order.");
    }
}
