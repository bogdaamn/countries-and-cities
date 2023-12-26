package com.cities.service;

import com.cities.config.jwt.JwtTokenUtil;
import com.cities.rest.dto.CredentialsDto;
import com.cities.rest.dto.JwtResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponseDto signIn(CredentialsDto credentialsDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentialsDto.getLogin(), credentialsDto.getPassword()));

        var userDetails = userDetailsService.loadUserByUsername(credentialsDto.getLogin());
        var jwt = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponseDto(jwt);
    }
}
