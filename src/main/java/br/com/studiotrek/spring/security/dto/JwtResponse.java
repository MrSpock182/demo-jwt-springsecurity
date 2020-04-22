package br.com.studiotrek.spring.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse {

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
