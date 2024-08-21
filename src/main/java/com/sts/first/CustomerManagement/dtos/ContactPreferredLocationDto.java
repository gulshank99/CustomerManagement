package com.sts.first.CustomerManagement.dtos;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPreferredLocationDto {
    private Long id;
    private ContactDetailsDto contactDetails;
    private MasterLocationDto location;
}