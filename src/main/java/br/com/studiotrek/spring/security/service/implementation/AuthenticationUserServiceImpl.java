package br.com.studiotrek.spring.security.service.implementation;

import br.com.studiotrek.spring.security.domain.dto.JwtRequest;
import br.com.studiotrek.spring.security.domain.orm.User;
import br.com.studiotrek.spring.security.service.AuthenticationUserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

	@Override public User auth(JwtRequest request) {
		return new User(request.getUsername(),
				request.getPassword(),
				true,
				request.getRoles());
	}
}
