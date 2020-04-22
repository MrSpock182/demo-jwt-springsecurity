package br.com.studiotrek.spring.security.service.implementation;

import br.com.studiotrek.spring.security.dto.JwtRoles;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceWithJwt implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username,
                "password",
                true,
                false,
                false,
                false,
                Arrays.asList(JwtRoles.ROLE_USER, JwtRoles.ROLE_ADMIN));
    }

}