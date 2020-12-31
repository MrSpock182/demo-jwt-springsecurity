package br.com.studiotrek.spring.security.api;

import br.com.studiotrek.spring.security.domain.dto.JwtRequest;
import br.com.studiotrek.spring.security.domain.dto.JwtResponse;
import br.com.studiotrek.spring.security.service.ValidationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationApi {

    @Autowired
    private ValidationUserService validationUser;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest auth) {
        return new JwtResponse(validationUser.authenticate(auth));
    }

}
