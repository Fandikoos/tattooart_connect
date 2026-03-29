package com.almozara.tattooart_connect.dto;

import com.almozara.tattooart_connect.util.enums.BookingStatusEnum;
import com.almozara.tattooart_connect.util.enums.BookingTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @Schema(description = "ID de la reserva", example = "1")
    private Long idBooking;

    @Schema(description = "Título o asunto de la reserva / nota", example = "Tatuaje floral - Marta")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Schema(description = "Descripción adicional o notas", example = "Tatuaje en el antebrazo, diseño personalizado")
    private String description;

    @Schema(description = "Fecha y hora de inicio", example = "2026-04-10T10:00:00")
    @NotNull(message = "Start date and time is required")
    private LocalDateTime startDateTime;

    @Schema(description = "Fecha y hora de fin", example = "2026-04-10T12:00:00")
    private LocalDateTime endDateTime;

    @Schema(description = "Tipo de entrada: BOOKING, NOTE, BLOCK_TIME", example = "BOOKING")
    @NotNull(message = "Type is required")
    private BookingTypeEnum type;

    @Schema(description = "Estado de la reserva: PENDING, CONFIRMED, CANCELLED, COMPLETED", example = "PENDING")
    @NotNull(message = "Status is required")
    private BookingStatusEnum status;

    @Schema(description = "Nombre del cliente", example = "Marta García")
    private String clientName;

    @Schema(description = "Teléfono del cliente", example = "612345678")
    private String clientPhone;

    @Schema(description = "Email del cliente", example = "marta@email.com")
    @Email(message = "Client email is not valid")
    private String clientEmail;

    @Schema(description = "Color del evento en el calendario (hex)", example = "#FF5733")
    private String color;

    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última modificación")
    private LocalDateTime updatedAt;

    @Schema(description = "ID del estudio al que pertenece la reserva", example = "1")
    @NotNull(message = "Studio ID is required")
    private Long idTattooStudio;

    @Schema(description = "ID del artista asignado a la reserva (opcional)", example = "2")
    private Long idArtist;
}