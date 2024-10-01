package com.sts.first.CustomerManagement.dtos;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPreferredLocationDto {
    private Long prefLocationId;
    @NotNull(message = "Contact details are required !!",groups = CreateValidation.class)
    private ContactDetailsDto contactDetails;

    @NotNull(message = "Location details are required !!", groups = CreateValidation.class)
     private MasterLocationDto location;
}