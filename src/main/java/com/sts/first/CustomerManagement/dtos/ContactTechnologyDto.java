package com.sts.first.CustomerManagement.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTechnologyDto {
    private Long id;
    private Integer experience;
    private String expertiseLevel;
    private Boolean isPrimary;
    private Boolean isSecondary;
    private ContactDetailsDto contactDetails;
    private MasterTechnologyDto technology;
}