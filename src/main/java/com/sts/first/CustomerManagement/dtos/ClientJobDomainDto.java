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
public class ClientJobDomainDto {
    private Long clientJobDomainId;

//    @NotNull(message = "Primary domain status is required !!", groups = CreateValidation.class)
//    // @AssertTrue(message = "IsPrimaryDomain flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
//    private Boolean isPrimaryDomain;
//
//    @NotNull(message = "Secondary domain status is required !!", groups = CreateValidation.class)
//    // @AssertTrue(message = "IsSecondaryDomain flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
//    private Boolean isSecondaryDomain;

    @NotNull(message = "ClientJob  is required !!", groups = CreateValidation.class)
    private  ClientJobDto clientJob; ;

    @NotNull(message = "Domain is required !!", groups = CreateValidation.class)
    private MasterDomainDto domain;
}
