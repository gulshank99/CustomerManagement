package com.sts.first.CustomerManagement.dtos;

import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTechnologyDto {
    private Long contactTechId;

    @NotNull(message = "Experience is required !!", groups = CreateValidation.class)
    @Digits(integer = 3, fraction = 0, message = "Experience must be a whole number !!", groups = {CreateValidation.class, UpdateValidation.class})
    private Integer experience;

    @NotBlank(message = "Expertise level is required !!", groups = CreateValidation.class)
    @Size(min = 1, max = 50, message = "Expertise level needed !!", groups = {CreateValidation.class, UpdateValidation.class})
    private String expertiseLevel;

    @NotNull(message = "Primary flag is required !!", groups = CreateValidation.class)
    @AssertTrue(message = "Primary flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
    private Boolean isPrimary;

    @NotNull(message = "Secondary flag is required !!", groups = CreateValidation.class)
    @AssertTrue(message = "Secondary flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
    private Boolean isSecondary;

    @NotNull(message = "Contact details are required !!", groups = CreateValidation.class)
    private ContactDetailsDto contactDetails;

    @NotNull(message = "Technology details are required !!", groups = CreateValidation.class)
    private MasterTechnologyDto technology;
}