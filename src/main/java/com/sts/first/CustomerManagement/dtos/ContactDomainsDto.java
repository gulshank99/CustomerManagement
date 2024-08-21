package com.sts.first.CustomerManagement.dtos;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDomainsDto {
    private Long id;
    private Boolean isPrimaryDomain;
    private Boolean isSecondaryDomain;
    private ContactDetailsDto contactDetails;
    private MasterDomainDto domain;
}