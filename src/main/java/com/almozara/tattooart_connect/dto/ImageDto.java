package com.almozara.tattooart_connect.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private Long idImage;
    @NotNull(message = "Name of image can't be null")
    private String storedName;
    private String url;
    private LocalDateTime uploadedAt;
    private Long idStudio;
}
