package com.ftn.Chickito.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtAuthenticationRequest {

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }
}
