package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRoundDto {
    private Long roundId;
    @NotNull(message = "Round number is required !!", groups = {CreateValidation.class })
    @Positive(message = "Round number must be a positive number !!", groups = {CreateValidation.class, UpdateValidation.class})
    @Digits(integer = 2, fraction = 0, message = "Round number must be a whole number !!", groups = {CreateValidation.class, UpdateValidation.class})
    private Integer roundNumber;

    @NotNull(message = "Round date is required !!", groups = {CreateValidation.class })
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate roundDate;

    @NotBlank(message = "Interviewer name is required !!", groups = {CreateValidation.class })
    @Size(min = 1, max = 100, message = "Interviewer name Required !!", groups = {CreateValidation.class, UpdateValidation.class})
    private String interviewerName;

    @NotBlank(message = "Technology interviewed is required !!", groups = {CreateValidation.class })
    @Size(min = 1, max = 100, message = "Technology interviewed required !!", groups = {CreateValidation.class, UpdateValidation.class})
    private String technologyInterviewed;

    @NotNull(message = "Tech rating is required !!", groups = {CreateValidation.class })
    @Min(value = 1, message = "Tech rating must be at least 1 !!", groups = {CreateValidation.class, UpdateValidation.class})
    @Max(value = 10, message = "Tech rating can be at most 10 !!",  groups = {CreateValidation.class, UpdateValidation.class})
    private Integer techRating;

    @NotNull(message = "Soft skills rating is required !!", groups = {CreateValidation.class })
    @Min(value = 1, message = "Soft skills rating must be at least 1 !!", groups = {CreateValidation.class, UpdateValidation.class})
    @Max(value = 10, message = "Soft skills rating can be at most 10 !!",  groups = {CreateValidation.class, UpdateValidation.class})
    private Integer softskillsRating;

    @NotBlank(message = "Interview status is required !!", groups = {CreateValidation.class })
    @Size(min = 1, max = 20, message = "Interview status required !!", groups = {CreateValidation.class, UpdateValidation.class})
    private String interviewStatus;

    @NotBlank(message = "Remarks are required !!", groups = {CreateValidation.class })
    @Size(min = 1, max = 500, message = "Remarks required!!", groups = {CreateValidation.class, UpdateValidation.class})
    private String remarks;

    @NotNull(message = "Interview details are required !!", groups = {CreateValidation.class })
    private ContactInterviewsDto interview;

//    @NotNull(message = "Contact details are required !!")
//    private ContactDetailsDto contactDetails;
}