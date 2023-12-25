package com.cities.service;

import com.cities.exception.InvalidPasswordException;
import com.cities.exception.UserNotFoundException;
import com.cities.mapping.UserDtoMapper;
import com.cities.persistance.entity.UserEntity;
import com.cities.persistance.repository.UserRepository;
import com.cities.rest.dto.CredentialsDto;
import com.cities.rest.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.nio.CharBuffer;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDtoMapper userMapper;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserDtoMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDto authenticate(CredentialsDto credentialsDto) {
        UserEntity user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.map(user);
        }
        throw new InvalidPasswordException("Invalid password");
    }

    public UserDto findByLogin(String login) {
        UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("Login not found"));
        return userMapper.map(user);
    }
}
