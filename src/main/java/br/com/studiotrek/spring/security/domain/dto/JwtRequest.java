package br.com.studiotrek.spring.security.domain.dto;

import br.com.studiotrek.spring.security.domain.enumerable.JwtRoles;

public class JwtRequest {
    private final String username;
    private final String password;
    private final JwtRoles role;

    public JwtRequest(String username, String password, JwtRoles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public JwtRoles getRole() {
        return role;
    }
}
