package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRoundDto {
    private Long roundId;
    private Integer roundNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate roundDate;
    private String interviewerName;
    private String technologyInterviewed;
    private Integer techRating;
    private Integer softskillsRating;
    private String remarks;
    private ContactInterviewsDto interview;

    private ContactDetailsDto contactDetails;
}