package com.almozara.tattooart_connect.security.service;

import com.almozara.tattooart_connect.global.exceptions.DuplicateResourceException;
import com.almozara.tattooart_connect.global.exceptions.message.UserValidation;
import com.almozara.tattooart_connect.mapper.UserMapper;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.dto.CreateUserDto;
import com.almozara.tattooart_connect.security.dto.JwtTokenDto;
import com.almozara.tattooart_connect.security.dto.LoginUserDto;
import com.almozara.tattooart_connect.security.dto.ProfileUserDto;
import com.almozara.tattooart_connect.security.jwt.JwtProvider;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import com.almozara.tattooart_connect.util.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public CreateUserDto create(CreateUserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new DuplicateResourceException(UserValidation.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateResourceException(UserValidation.EMAIL_ALREADY_EXISTS);
        }

        // Siempre asignar ROLE_USER, ignorando lo que venga en el DTO
        List<RoleEnum> roles = List.of(RoleEnum.ROLE_USER);

        UserEntity userEntity = userMapper.transferCreateUserDtoToEntity(userDto);
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        userEntity.setRoles(roles);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.transferToCreateUserDto(savedUser);

    }

    public CreateUserDto createAdmin(CreateUserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new DuplicateResourceException(UserValidation.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateResourceException(UserValidation.EMAIL_ALREADY_EXISTS);
        }

        List<String> rolesAdmin = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        userDto.setRoles(rolesAdmin);

        UserEntity userEntity = userMapper.transferCreateUserDtoToEntity(userDto);
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.transferToCreateUserDto(savedUser);
    }

    public CreateUserDto createUser(CreateUserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new DuplicateResourceException(UserValidation.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateResourceException(UserValidation.EMAIL_ALREADY_EXISTS);
        }

        List<String> roleUser = List.of("ROLE_USER");
        userDto.setRoles(roleUser);

        UserEntity userEntity = userMapper.transferCreateUserDtoToEntity(userDto);
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.transferToCreateUserDto(savedUser);
    }

    public JwtTokenDto login(LoginUserDto dto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException(UserValidation.USER_NOT_FOUND));
        ProfileUserDto userDto = userMapper.transferProfileUserDto(user);
        return new JwtTokenDto(token, userDto);
    }

    public String findUsernameByIdUser(Long idUser) {
        return userRepository.findUsernameByIdUser(idUser);
    }
}
