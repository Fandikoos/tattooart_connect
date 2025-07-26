package com.almozara.tattooart_connect.security.domain;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.util.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = UserEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    public static final String TABLE_NAME = "ET_USER";

    public static final String ID_USER_COLUMN = "ID_USER";
    public static final String USERNAME_COLUMN = "USERNAME";
    public static final String EMAIL_COLUMN = "EMAIL";
    public static final String PASSWORD_COLUMN = "PASSWORD";
    public static final String ROLE_COLUMN = "ROLE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_USER_COLUMN)
    private Long idUser;

    @Column(name = USERNAME_COLUMN)
    private String username;

    @Column(name = EMAIL_COLUMN)
    private String email;

    @Column(name = PASSWORD_COLUMN)
    private String password;

    // Colección de roles, se crea una tabla de roles para gestionarlo
    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "ET_USER_ROLES",
            joinColumns = @JoinColumn(name = ID_USER_COLUMN),
            foreignKey = @ForeignKey(name = "fk_user_roles")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = ROLE_COLUMN)
    private List<RoleEnum> roles;

}
