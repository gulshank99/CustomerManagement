package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Interview date is required !!")
    private LocalDate interviewDate;

    @NotBlank(message = "Interview status is required !!")
    @NotNull(message = "Interview Status is required !!")
    private String interviewStatus;

    @NotNull(message = "Client ID details are required !!")
    @NotBlank(message = "Client ID status is required !!")
    private MasterClientDto client;

    @NotNull(message = "Contact ID details are required !!")
    @NotBlank(message = "Contact ID status is required !!")
    private ContactDetailsDto contactDetails;
}