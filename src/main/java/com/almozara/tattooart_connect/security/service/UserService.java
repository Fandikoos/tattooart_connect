package com.almozara.tattooart_connect.security.service;

import com.almozara.tattooart_connect.mapper.UserMapper;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.dto.CreateUserDto;
import com.almozara.tattooart_connect.security.dto.JwtTokenDto;
import com.almozara.tattooart_connect.security.dto.LoginUserDto;
import com.almozara.tattooart_connect.security.jwt.JwtProvider;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import com.almozara.tattooart_connect.util.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    public CreateUserDto create(CreateUserDto userDto){
        if (userRepository.existsByUsername(userDto.getUsername())){
            throw new RuntimeException("Username already in use");
        }
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new RuntimeException("Email already in use");
        }

        // Transformar lista de roles (string) del dto a RoleEnum
        List<RoleEnum> roles = userDto.getRoles().stream().map(RoleEnum::valueOf).toList();

        UserEntity userEntity = userMapper.transferCreateUserDtoToEntity(userDto);
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        userEntity.setRoles(roles);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.transferToCreateUserDto(savedUser);

    }

    public CreateUserDto createAdmin(CreateUserDto userDto){
        if (userRepository.existsByUsername(userDto.getUsername())){
            throw new RuntimeException("Username already in use");
        }
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new RuntimeException("Email already in use");
        }

        List<String> rolesAdmin = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        userDto.setRoles(rolesAdmin);

        UserEntity userEntity = userMapper.transferCreateUserDtoToEntity(userDto);
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.transferToCreateUserDto(savedUser);
    }

    public CreateUserDto createUser(CreateUserDto userDto){
        if (userRepository.existsByUsername(userDto.getUsername())){
            throw new RuntimeException("Username already in use");
        }
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new RuntimeException("Email already in use");
        }

        List<String> roleUser = List.of("ROLE_USER");
        userDto.setRoles(roleUser);

        UserEntity userEntity = userMapper.transferCreateUserDtoToEntity(userDto);
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.transferToCreateUserDto(savedUser);
    }

    public JwtTokenDto login(LoginUserDto dto){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }
}
