package com.sts.first.CustomerManagement.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterClientDto {
    private Long clientId;
    @NotBlank(message = "Client name is required !!")
    private String clientName;

    @NotBlank(message = "Address is required !!")
    private String address;

    @NotBlank(message = "HR contact person is required !!")
    private String hrContactPerson;

    @NotBlank(message = "Technical person is required !!")
    private String technicalPerson;

    @Min(value = 1000000000L, message = "Phone number must be exactly 10 digits.")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits. !!")
    @NotNull(message = "Primary Phone number is required !!")
    private Long primaryMobile;

    @Min(value = 1000000000L, message = "Phone number must be exactly 10 digits.")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits. !!")
    private Long secondaryNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate insertedOn;

    @NotNull(message = "Location details are required !!")
    @NotBlank(message = "Location details is required !!")
    private MasterLocationDto location;
}