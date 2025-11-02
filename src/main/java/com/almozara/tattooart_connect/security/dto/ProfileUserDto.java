package com.almozara.tattooart_connect.security.dto;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.dto.StudioImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserDto {

    private Long idUser;
    private String username;
    private String email;
    private int phone;
    private String description;
    private List<StudioDto> studios;
}
