package br.com.studiotrek.spring.security.service;

import br.com.studiotrek.spring.security.domain.orm.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface SpringAuthenticationService {
    Claims getAllClaimsFromToken(String token);

    String getUsernameFromToken(String token);

    Date getExpirationDateFromToken(String token);

    String generateToken(User user);

    Boolean validateToken(String token);

    Boolean validateToken(String token, UserDetails userDetails);
}
