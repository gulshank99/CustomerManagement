package com.sts.first.CustomerManagement.dtos;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRoundDto {
    private Long roundId;
    private Integer roundNumber;
    private LocalDate roundDate;
    private String interviewerName;
    private String technologyInterviewed;
    private Integer techRating;
    private Integer softskillsRating;
    private String remarks;
    private ContactInterviewsDto interview;
}