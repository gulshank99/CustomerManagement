package com.sts.first.CustomerManagement.dtos;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterDomainDto {
    private Long domainId;
    private String domainDetails;
    private LocalDate insertedOn;
}