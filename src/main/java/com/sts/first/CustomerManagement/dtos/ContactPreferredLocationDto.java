package com.sts.first.CustomerManagement.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPreferredLocationDto {
    private Long prefLocationId;
    @NotNull(message = "Contact details are required !!")
    @NotBlank(message = "Contact details is required !!")
    private ContactDetailsDto contactDetails;

    @NotNull(message = "Location details are required !!")
    @NotBlank(message = "Location details is required !!")
     private MasterLocationDto location;
}