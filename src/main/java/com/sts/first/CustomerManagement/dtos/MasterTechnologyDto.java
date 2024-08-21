package com.sts.first.CustomerManagement.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterTechnologyDto {
    private Long techId;
    private String technology;
    private LocalDateTime insertedOn;
}
