package br.com.studiotrek.spring.security.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test-one")
    @PreAuthorize("hasRole('USER')")
    public String testOne() {
        return "SUCESS";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test-two")
    @PreAuthorize("hasRole('ADMIN')")
    public String testTwo() {
        return "SUCESS";
    }

}
