package com.almozara.tattooart_connect.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
//    @NotEmpty(message = "Roles are required")
    private List<String> roles = new ArrayList<>();
}
