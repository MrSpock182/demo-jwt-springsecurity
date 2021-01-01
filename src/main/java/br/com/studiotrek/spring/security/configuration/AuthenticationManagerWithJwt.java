package br.com.studiotrek.spring.security.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.studiotrek.spring.security.domain.enumerable.JwtRoles;
import br.com.studiotrek.spring.security.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class AuthenticationManagerWithJwt extends OncePerRequestFilter {

    private final String uriAuth;
    private final JwtService authService;

    public AuthenticationManagerWithJwt(final @Value("${uri.authenticate}") String uriAuth,
            final JwtService authService) {
        this.uriAuth = uriAuth;
        this.authService = authService;
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

        if (Boolean.TRUE.equals(authService.validateToken(token))) {
            Claims claims = authService.getAllClaimsFromToken(token);
            List<String> rolesMap = claims.get("role", List.class);
            List<JwtRoles> roles = new ArrayList<>();
            rolesMap.forEach(map -> roles.add(JwtRoles.valueOf(map)));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList())
            );
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
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
