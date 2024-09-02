package com.sts.first.CustomerManagement.dtos;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDomainsDto {
    private Long contactDomainId;

    @NotNull(message = "Primary domain status is required !!")
    private Boolean isPrimaryDomain;

    @NotNull(message = "Secondary domain status is required !!")
    private Boolean isSecondaryDomain;

    @NotNull(message = "ContactDetails is required !!")
    @NotBlank(message="ContactDetails required !!")
    private ContactDetailsDto contactDetails;

    @NotNull(message = "Domain is required !!")
    private MasterDomainDto domain;
}