package com.almozara.tattooart_connect.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserDto {

    private Long idUser;
    private String username;
    private String email;
    private int phone;
    private String description;
}
