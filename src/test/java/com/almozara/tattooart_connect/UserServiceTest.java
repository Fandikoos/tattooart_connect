package com.almozara.tattooart_connect;

import com.almozara.tattooart_connect.global.exceptions.DuplicateResourceException;
import com.almozara.tattooart_connect.mapper.UserMapper;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.dto.CreateUserDto;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import com.almozara.tattooart_connect.security.service.UserService;
import com.almozara.tattooart_connect.util.enums.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreate() {
        CreateUserDto userToCreate = CreateUserDto.builder()
                .username("TEST-001")
                .email("test@gmail.com")
                .password("testpassword")
                .roles(Collections.singletonList(RoleEnum.ROLE_USER.name()))
                .build();
        UserEntity userCreated = UserEntity.builder()
                .idUser(1L)
                .username("TEST-001")
                .email("test@gmail.com")
                .password("testpassword")
                .roles(Collections.singletonList(RoleEnum.ROLE_USER))
                .build();

        when(userMapper.transferCreateUserDtoToEntity(userToCreate)).thenReturn(userCreated);
        when(userRepository.save(userCreated)).thenReturn(userCreated);
        when(passwordEncoder.encode(userToCreate.getPassword())).thenReturn("testpassword");
        when(userMapper.transferToCreateUserDto(userCreated)).thenReturn(userToCreate);

        CreateUserDto createUser = userService.create(userToCreate);
        assertNotNull(createUser);
        assertEquals(userCreated.getUsername(), createUser.getUsername());
        assertEquals(userCreated.getPassword(), createUser.getPassword());
    }

    @Test
    void duplicateEmail() {
        CreateUserDto userToCreateWithSameEmail = CreateUserDto.builder()
                .username("TEST-002")
                .email("test@gmail.com")
                .password("testpassword")
                .roles(Collections.singletonList(RoleEnum.ROLE_USER.name()))
                .build();

        when(userRepository.existsByUsername(userToCreateWithSameEmail.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(userToCreateWithSameEmail.getEmail())).thenReturn(true);

        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, () ->
                userService.create(userToCreateWithSameEmail)
        );
        assertEquals("EMAIL_ALREADY_EXISTS", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void duplicateUsername() {
        CreateUserDto userToCreateWithSameUsername = CreateUserDto.builder()
                .username("TEST-001")
                .email("test@gmail.com")
                .password("testpassword")
                .roles(Collections.singletonList(RoleEnum.ROLE_USER.name()))
                .build();

        when(userRepository.existsByUsername(userToCreateWithSameUsername.getUsername())).thenReturn(true);

        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, () ->
                userService.create(userToCreateWithSameUsername)
        );
        assertEquals("USERNAME_ALREADY_EXISTS", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldFindUsernameByIdUser() {
        Long idUser = 1L;
        String expectedUsername = "TEST-001";
        when(userRepository.findUsernameByIdUser(idUser)).thenReturn(expectedUsername);
        String username = userService.findUsernameByIdUser(idUser);
        assertNotNull(username);
        assertEquals(expectedUsername, username);
        assertEquals(expectedUsername.length(), username.length());
    }

}
