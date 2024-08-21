package com.sts.first.CustomerManagement.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterLocationDto {
    private Long locationId;
    private String locationDetails;
    private LocalDateTime insertedOn;
}