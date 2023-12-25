package com.cities.service;

import com.cities.exception.UsernameNotFoundException;
import com.cities.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        Collection<String> mappedAuthorities = Arrays.asList(user.getRole().name().split(","));
        User userDetails = new User(username,
                new BCryptPasswordEncoder().encode(user.getPassword()),
                mappedAuthorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        return userDetails;
    }
}