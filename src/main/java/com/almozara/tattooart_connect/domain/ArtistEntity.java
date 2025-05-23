package com.almozara.tattooart_connect.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name =ArtistEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class ArtistEntity {

    // Nombre tabla
    public static final String TABLE_NAME = "ET_ARTIST";

    // Columnas
    private static final String ID_ARTIST_COLUMN = "ID_ARTIST";
    private static final String NAME_COLUMN = "NAME";
    private static final String SURNAME_COLUMN = "SURNAME";
    private static final String SECOND_SURNAME_COLUMN = "SECOND_SURNAME";
    private static final String EMAIL_COLUMN = "EMAIL";
    private static final String DNI_COLUMN = "DNI";
    private static final String PHONE_COLUMN = "PHONE";
    private static final String TATTOO_STUDIO_COLUMN = "TATTOO_STUDIO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtist;

    @Column(name = ArtistEntity.NAME_COLUMN)
    @NotNull
    private String name;

    @Column(name = ArtistEntity.SURNAME_COLUMN)
    @NotNull
    private String surname;

    @Column(name = ArtistEntity.SECOND_SURNAME_COLUMN)
    @NotNull
    private String secondSurname;

    @Column(name = ArtistEntity.EMAIL_COLUMN)
    @Email
    private String email;

    @Column(name = ArtistEntity.DNI_COLUMN)
    @NotNull
    private String dni;

    @Column(name = ArtistEntity.PHONE_COLUMN)
    @NotNull
    private int phone;
}
