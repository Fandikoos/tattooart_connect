package com.almozara.tattooart_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {

    private Long idArtist;
    private String name;
    private String surname;
    private String secondSurname;
    private String email;
    private String dni;
    private int phone;
    private String imageArtist;
    private Long idTattooStudio;

}
