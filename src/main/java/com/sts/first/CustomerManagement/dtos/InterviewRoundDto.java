package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @NotNull(message = "Round number is required !!")
    @Min(value = 1, message = "Round number must be at least 1 !!")
    private Integer roundNumber;

    @NotNull(message = "Round date is required !!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate roundDate;

    @NotBlank(message = "Interviewer name is required !!")
    @Size(min = 1, max = 100, message = "Interviewer name can be up to 100 characters long !!")
    private String interviewerName;

    @NotBlank(message = "Technology interviewed is required !!")
    @Size(min = 1, max = 100, message = "Technology interviewed can be up to 100 characters long !!")
    private String technologyInterviewed;

    @NotNull(message = "Tech rating is required !!")
    @Min(value = 1, message = "Tech rating must be at least 1 !!")
    @Max(value = 10, message = "Tech rating can be at most 10 !!")
    private Integer techRating;

    @NotNull(message = "Soft skills rating is required !!")
    @Min(value = 1, message = "Soft skills rating must be at least 1 !!")
    @Max(value = 10, message = "Soft skills rating can be at most 10 !!")
    private Integer softskillsRating;

    @NotBlank(message = "Remarks are required !!")
    @Size(min = 1, max = 500, message = "Remarks can be up to 500 characters long !!")
    private String remarks;

    @NotNull(message = "Interview details are required !!")
    private ContactInterviewsDto interview;

//    @NotNull(message = "Contact details are required !!")
//    private ContactDetailsDto contactDetails;
}