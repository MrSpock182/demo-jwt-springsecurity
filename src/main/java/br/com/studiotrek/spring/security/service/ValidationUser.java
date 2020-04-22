package br.com.studiotrek.spring.security.service;

import br.com.studiotrek.spring.security.dto.JwtRequest;

public interface ValidationUser {
    String authenticate(JwtRequest auth);
}
