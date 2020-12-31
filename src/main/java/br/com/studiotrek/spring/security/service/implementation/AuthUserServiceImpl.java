package br.com.studiotrek.spring.security.service.implementation;

import br.com.studiotrek.spring.security.domain.dto.JwtRequest;
import br.com.studiotrek.spring.security.domain.orm.User;
import br.com.studiotrek.spring.security.service.AuthUserService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Override public User auth(JwtRequest request) {
		return new User(request.getUsername(),
				request.getPassword(),
				true,
				Collections.singletonList(request.getRole()));
	}
}
