package com.almozara.tattooart_connect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {

    private Long idArtist;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String secondSurname;
    @Email
    private String email;
    @NotNull
    private String dni;
    @NotNull
    private int phone;
    private String imageArtist;
    private Long idTattooStudio;

}
