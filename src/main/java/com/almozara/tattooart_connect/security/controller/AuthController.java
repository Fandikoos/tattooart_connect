package com.almozara.tattooart_connect.security.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.controller.ArtistController;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.dto.CreateUserDto;
import com.almozara.tattooart_connect.security.dto.JwtTokenDto;
import com.almozara.tattooart_connect.security.dto.LoginUserDto;
import com.almozara.tattooart_connect.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + AuthController.URL)
public class AuthController {

    public static final String URL = "/auth";

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<CreateUserDto> create(@Valid @RequestBody CreateUserDto userDto){
        return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@Valid @RequestBody LoginUserDto userDto){
        return new ResponseEntity<>(userService.login(userDto), HttpStatus.CREATED);
    }
}
