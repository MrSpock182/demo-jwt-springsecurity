package br.com.studiotrek.spring.security.service.implementation;

import br.com.studiotrek.spring.security.domain.dto.JwtRequest;
import br.com.studiotrek.spring.security.service.AuthUserService;
import br.com.studiotrek.spring.security.service.ValidationUserService;
import org.springframework.stereotype.Service;

@Service
public class ValidationUserServiceImpl implements ValidationUserService {

    private final AuthUserService authUserService;
    private final SpringAuthenticationServiceImpl jwtTokenUtil;

    public ValidationUserServiceImpl(final AuthUserService authUserService,
            final SpringAuthenticationServiceImpl jwtTokenUtil) {
        this.authUserService = authUserService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String authenticate(JwtRequest auth) {
        return jwtTokenUtil.generateToken(
                authUserService.auth(auth));
    }
}
