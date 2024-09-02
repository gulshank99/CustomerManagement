package com.sts.first.CustomerManagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTechnologyDto {
    private Long contactTechId;
    @NotNull(message = "Experience is required !!")
    private Integer experience;
    @NotNull(message = "Expertise level is required !!")
    @Size(min = 1, max = 50, message = "Expertise level must be between 1 and 50 characters !!")
    private String expertiseLevel;
    @NotNull(message = "Primary flag is required !!")
    private Boolean isPrimary;
    @NotNull(message = "Secondary flag is required !!")
    private Boolean isSecondary;
    @NotNull(message = "Contact details are required !!")
    @NotBlank(message = "Contact details is required !!")
    private ContactDetailsDto contactDetails;
    @NotNull(message = "Technology details are required !!")
    @NotBlank(message = "Technology is required !!")
    private MasterTechnologyDto technology;
}