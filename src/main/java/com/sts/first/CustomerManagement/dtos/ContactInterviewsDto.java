package com.sts.first.CustomerManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInterviewsDto {
    private Long interviewId;
    private LocalDate interviewDate;
    private String interviewStatus;
    private ContactDetailsDto contactDetails;
    private MasterClientDto client;
}