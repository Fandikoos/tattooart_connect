package com.almozara.tattooart_connect.domain;

import com.almozara.tattooart_connect.util.enums.BookingStatusEnum;
import com.almozara.tattooart_connect.util.enums.BookingTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = BookingEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookingEntity {

    public static final String TABLE_NAME = "ET_BOOKING";

    public static final String ID_BOOKING_COLUMN = "ID_BOOKING";
    public static final String TITLE_COLUMN = "TITLE";
    public static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    public static final String START_DATE_TIME_COLUMN = "START_DATE_TIME";
    public static final String END_DATE_TIME_COLUMN = "END_DATE_TIME";
    public static final String TYPE_COLUMN = "TYPE";
    public static final String STATUS_COLUMN = "STATUS";
    public static final String CLIENT_NAME_COLUMN = "CLIENT_NAME";
    public static final String CLIENT_PHONE_COLUMN = "CLIENT_PHONE";
    public static final String CLIENT_EMAIL_COLUMN = "CLIENT_EMAIL";
    public static final String COLOR_COLUMN = "COLOR";
    public static final String CREATED_AT_COLUMN = "CREATED_AT";
    public static final String UPDATED_AT_COLUMN = "UPDATED_AT";
    public static final String TATTOO_STUDIO_COLUMN = "ID_STUDIO";
    public static final String ARTIST_COLUMN = "ID_ARTIST";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_BOOKING_COLUMN)
    @EqualsAndHashCode.Include
    private Long idBooking;

    @Column(name = TITLE_COLUMN, nullable = false)
    private String title;

    @Column(name = DESCRIPTION_COLUMN, columnDefinition = "TEXT")
    private String description;

    @Column(name = START_DATE_TIME_COLUMN, nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = END_DATE_TIME_COLUMN)
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = TYPE_COLUMN, nullable = false)
    private BookingTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = STATUS_COLUMN, nullable = false)
    private BookingStatusEnum status;

    // Datos del cliente (no requiere ser usuario registrado)
    @Column(name = CLIENT_NAME_COLUMN)
    private String clientName;

    @Column(name = CLIENT_PHONE_COLUMN)
    private String clientPhone;

    @Column(name = CLIENT_EMAIL_COLUMN)
    private String clientEmail;

    // Color del evento para el calendario en el frontend (hex)
    @Column(name = COLOR_COLUMN)
    private String color;

    @CreationTimestamp
    @Column(name = CREATED_AT_COLUMN, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = UPDATED_AT_COLUMN)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TATTOO_STUDIO_COLUMN, referencedColumnName = StudioEntity.ID_STUDIO_COLUMN, nullable = false)
    private StudioEntity tattooStudio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ARTIST_COLUMN, referencedColumnName = ArtistEntity.ID_ARTIST_COLUMN)
    private ArtistEntity artist;
}