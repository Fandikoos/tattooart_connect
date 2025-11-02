package com.almozara.tattooart_connect.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDto {
    private String token;
    private ProfileUserDto userProfile;
}
