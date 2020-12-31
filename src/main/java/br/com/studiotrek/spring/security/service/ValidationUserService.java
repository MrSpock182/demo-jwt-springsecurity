package br.com.studiotrek.spring.security.service;

import br.com.studiotrek.spring.security.domain.dto.JwtRequest;

public interface ValidationUserService {
    String authenticate(JwtRequest auth);
}
