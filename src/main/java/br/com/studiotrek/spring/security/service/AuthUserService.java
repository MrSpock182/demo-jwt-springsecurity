package br.com.studiotrek.spring.security.service;

import br.com.studiotrek.spring.security.domain.dto.JwtRequest;
import br.com.studiotrek.spring.security.domain.orm.User;

public interface AuthUserService {
	User auth(JwtRequest request);
}
