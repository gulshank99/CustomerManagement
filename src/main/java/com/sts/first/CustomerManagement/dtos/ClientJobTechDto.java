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
public class ClientJobTechDto {

        private Long jobCodeTechId;

        @NotNull(message = "Job required !!", groups = {CreateValidation.class })
        private ClientJobDto job;

        @NotNull(message = "Technology required !!", groups = {CreateValidation.class })
        private MasterTechnologyDto technology;

        @NotNull(message = "Domain required !!", groups = {CreateValidation.class })
        private MasterDomainDto domain;
}
