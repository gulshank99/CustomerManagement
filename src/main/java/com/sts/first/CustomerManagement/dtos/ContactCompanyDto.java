package com.sts.first.CustomerManagement.dtos;

import com.sts.first.CustomerManagement.validate.CreateValidation;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ContactCompanyDto {
    private Long contactCompanyId;
    @NotNull(message = "Contact Details is required !!", groups = CreateValidation.class)
    private ContactDetailsDto contactDetails;

    @NotNull(message = "Company is required !!", groups = CreateValidation.class)
    private MasterCompanyDto company;
}
