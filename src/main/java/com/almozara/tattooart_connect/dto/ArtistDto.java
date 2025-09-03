package com.almozara.tattooart_connect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {

    private Long idArtist;
    @NotBlank(message = "Name can not be empty")
    @Size(max = 100, message = "Name have to be less than 100 character")
    private String name;
    @NotBlank(message = "Surname can not be empty")
    private String surname;
    @NotNull
    private String secondSurname;
    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Dni can not be empty")
    private String dni;
    @NotNull
    private int phone;
    private String imageArtist;
    private Long idTattooStudio;

}
