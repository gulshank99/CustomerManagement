package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sts.first.CustomerManagement.entities.ClientJob;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Interview status is required !!", groups = {CreateValidation.class})
    @Size(min = 3,max = 30,message = "Invalid Name !!", groups = {CreateValidation.class, UpdateValidation.class})
    private String interviewStatus;

//    @NotNull(message = "Client ID details are required !!", groups = {CreateValidation.class })
//    private MasterClientDto client;

    @NotNull(message = "Job ID details are required !!", groups = {CreateValidation.class })
    private ClientJob clientJob;

    @NotNull(message = "Contact ID details are required !!", groups = {CreateValidation.class })
    private ContactDetailsDto contactDetails;
}