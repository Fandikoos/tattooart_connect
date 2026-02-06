package com.almozara.tattooart_connect.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioDto {

    @Schema(description = "id Studio", example = "1")
    private Long idStudio;
    @Schema(description = "name", example = "AKT")
    @NotBlank(message = "Name can not be empty")
    private String name;
    @Schema(description = "address", example = "Avenida Almozara 75")
    @NotBlank(message = "Address can not be empty")
    private String address;
    @Schema(description = "latitud", example = "45.654")
    private Double latitud;
    @Schema(description = "longitud", example = "45.654")
    private Double longitud;
    @Schema(description = "rating", example = "4")
    @Min(value = 0, message = "Rating has to be more than 0")
    @Max(value = 5, message = "Rating has to be less than 5")
    private BigDecimal rating;
    @Schema(description = "description", example = "Studios famous for traditional designs")
    private String description;
    @Schema(description = "logo")
    private String logo;
    @Schema(description = "longitud", example = "10:55")
    @NotNull(message = "Open schedule is required")
    private LocalTime openSchedule;
    @Schema(description = "close schedule", example = "21:00")
    @NotNull(message = "Close shedule is required")
    private LocalTime closeSchedule;
    @Schema(description = "artists")
    private List<ArtistDto> artists;
    @Schema(description = "images gallery")
    private List<ImageDto> imagesGallery;
    @Schema(description = "review")
    private List<ReviewDto> reviews;
    @Schema(description = "idUser", example = "id User of admin")
    private Long idUser;
}
