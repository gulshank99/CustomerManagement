package com.sts.first.CustomerManagement.dtos;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDomainsDto {
    private Long contactDomainId;

    @NotNull(message = "Primary domain status is required !!", groups = CreateValidation.class)
   // @AssertTrue(message = "IsPrimaryDomain flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
    private Boolean isPrimaryDomain;

    @NotNull(message = "Secondary domain status is required !!", groups = CreateValidation.class)
   // @AssertTrue(message = "IsSecondaryDomain flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
    private Boolean isSecondaryDomain;

    @NotNull(message = "ContactDetails is required !!", groups = CreateValidation.class)
    private ContactDetailsDto contactDetails;

    @NotNull(message = "Domain is required !!", groups = CreateValidation.class)
    private MasterDomainDto domain;
}