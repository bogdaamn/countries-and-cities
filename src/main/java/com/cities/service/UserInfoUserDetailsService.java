package com.cities.service;

import com.cities.exception.UsernameNotFoundException;
import com.cities.mapping.UserInfoDetailsMapper;
import com.cities.persistance.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoDetailsMapper userInfoDetailsMapper;
    private final Logger logger = LoggerFactory.getLogger(UserInfoUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        logger.info("User info: name %s, password %s, role %s".formatted(user.getName(), user.getPassword(), user.getRole()));
        return userInfoDetailsMapper.toUserInfoDetails(user);
    }
}