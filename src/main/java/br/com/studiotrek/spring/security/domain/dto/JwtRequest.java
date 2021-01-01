package br.com.studiotrek.spring.security.domain.dto;

import br.com.studiotrek.spring.security.domain.enumerable.JwtRoles;

import java.util.List;

public class JwtRequest {
    private final String username;
    private final String password;
    private final List<JwtRoles> roles;

    public JwtRequest(final String username,
            final String password,
            final List<JwtRoles> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<JwtRoles> getRoles() {
        return roles;
    }
}
