package com.almozara.tattooart_connect.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = ArtistEntity.TABLE_NAME)
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
    public static final String IMAGE_ARTIST_COLUMN = "IMAGE_ARTIST";
    public static final String TATTOO_STUDIO_COLUMN = "ID_STUDIO_COLUMN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_ARTIST_COLUMN)
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
    private String phone;

    @Column(name = IMAGE_ARTIST_COLUMN)
    private String imageArtist;

    @ManyToOne
    @JoinColumn(name = TATTOO_STUDIO_COLUMN)
    private StudioEntity tattooStudio;
}
