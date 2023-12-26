package com.cities.rest.controller;

import com.cities.rest.dto.CredentialsDto;
import com.cities.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/authentication")
public class JwtAuthenticationController {

    private final AuthenticationService authenticationService;

    public JwtAuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody CredentialsDto credentialsDto) {
        var response = authenticationService.signIn(credentialsDto);
        return ResponseEntity.ok(response);
    }

}