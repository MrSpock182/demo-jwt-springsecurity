package br.com.studiotrek.spring.security.api;

import br.com.studiotrek.spring.security.dto.JwtRequest;
import br.com.studiotrek.spring.security.dto.JwtResponse;
import br.com.studiotrek.spring.security.service.ValidationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationApi {

    @Autowired
    private ValidationUser validationUser;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest auth) {
        return JwtResponse.builder()
                .token(validationUser.authenticate(auth))
                .build();
    }

}
