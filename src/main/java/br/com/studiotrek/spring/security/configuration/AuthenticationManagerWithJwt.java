package br.com.studiotrek.spring.security.configuration;

import java.io.IOException;

import br.com.studiotrek.spring.security.service.SpringAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class AuthenticationManagerWithJwt extends OncePerRequestFilter {

    private final String uriAuth;
    private final SpringAuthenticationService authService;
    private final UserDetailsServiceWithJwt service;

    public AuthenticationManagerWithJwt(final @Value("${uri.authenticate}") String uriAuth,
            final SpringAuthenticationService auth,
            final UserDetailsServiceWithJwt service) {
        this.uriAuth = uriAuth;
        this.authService = auth;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws ServletException, IOException {
        if(!uriAuth.equalsIgnoreCase(request.getRequestURI())) {
            validation(request);
        }
        chain.doFilter(request, response);
    }

    private void validation(final HttpServletRequest request) {
        final String token = getToken(request.getHeader("Authorization"));
        final String username = getUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(username);
            if (Boolean.TRUE.equals(authService.validateToken(token, userDetails))) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
    }

    private String getToken(final String requestTokenHeader) {
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        }
        return "";
    }

    private String getUsername(final String token) {
        return authService.getUsernameFromToken(token);
    }
}
