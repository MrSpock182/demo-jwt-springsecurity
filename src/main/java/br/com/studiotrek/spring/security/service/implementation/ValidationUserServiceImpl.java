package br.com.studiotrek.spring.security.service.implementation;

import br.com.studiotrek.spring.security.domain.dto.JwtRequest;
import br.com.studiotrek.spring.security.service.AuthenticationUserService;
import br.com.studiotrek.spring.security.service.ValidationUserService;
import org.springframework.stereotype.Service;

@Service
public class ValidationUserServiceImpl implements ValidationUserService {

    private final AuthenticationUserService authUserService;
    private final JwtServiceImpl jwtTokenUtil;

    public ValidationUserServiceImpl(final AuthenticationUserService authUserService,
            final JwtServiceImpl jwtTokenUtil) {
        this.authUserService = authUserService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String authenticate(JwtRequest auth) {
        return jwtTokenUtil.generateToken(
                authUserService.auth(auth));
    }
}
