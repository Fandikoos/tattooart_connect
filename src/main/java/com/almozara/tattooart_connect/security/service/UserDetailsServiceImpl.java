package com.almozara.tattooart_connect.security.service;

import com.almozara.tattooart_connect.global.exceptions.message.UserValidation;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsernameOrEmail(username, username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(UserValidation.EMAIL_OR_USERNAME_NOT_EXIST);
        }
        return UserPrincipal.builder(userEntity.get());
    }
}
