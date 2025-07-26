package com.almozara.tattooart_connect.security.service;

import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsernameOrEmail(username, username);
        if (!userEntity.isPresent()){
            return null;
        }
        return UserPrincipal.builder(userEntity.get());
    }
}
