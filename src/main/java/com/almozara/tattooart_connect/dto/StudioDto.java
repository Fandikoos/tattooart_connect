package com.almozara.tattooart_connect.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioDto {

    private Long idStudio;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Address can not be empty")
    private String address;
    private float latitud;
    private float longitud;
    @Min(value = 0, message = "Rating has to be more than 0")
    @Max(value = 5, message = "Rating has to be less than 5")
    private int rating;
    private String description;
    private String logo;
    @NotNull(message = "Open schedule is required")
    private LocalTime openSchedule;
    @NotNull(message = "Close shedule is required")
    private LocalTime closeSchedule;
    private List<ArtistDto> artists;
    private List<StudioImageDto> images;
}
