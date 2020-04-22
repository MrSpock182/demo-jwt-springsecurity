package br.com.studiotrek.spring.security.service.implementation;

import br.com.studiotrek.spring.security.dto.JwtRequest;
import br.com.studiotrek.spring.security.component.implementation.SpringAuthenticationWithJwt;
import br.com.studiotrek.spring.security.service.ValidationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ValidationUserImpl implements ValidationUser {

    @Autowired
    private SpringAuthenticationWithJwt jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String authenticate(JwtRequest auth) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());

        if(userDetails.getUsername().equalsIgnoreCase(auth.getUsername())
                && userDetails.getPassword().equalsIgnoreCase(auth.getPassword())) {
            return jwtTokenUtil.generateToken(auth);
        } else {
            return "USER DON'T EXISTS";
        }
    }
}
