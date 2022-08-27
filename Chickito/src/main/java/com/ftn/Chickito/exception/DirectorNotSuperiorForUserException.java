package com.ftn.Chickito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DirectorNotSuperiorForUserException extends RuntimeException{

    public DirectorNotSuperiorForUserException() {
        super("You are not superior for this user and because of that you are not able to approve and reject his vacation requests.");
    }
}
