package com.sts.first.CustomerManagement.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ContactDetailsDto {

    private Long contactId;
    private String emailId;
    private String contactName;
    private Long primaryNumber;
    private String designation;
    private Long secondaryNumber;
    private String otherSkills;
    private String companyName;
    private String resume;
    private String image;
    private Boolean isActive;
    private Double currentSalary;
    private String hiringType;
    private LocalDateTime dateTimeInserted;
    private String linkdinId;
    private String clientCompanyName;
    private String techStack;

    private List<ContactSkillsDto> skills;
    private List<PreferredLocationDto> preferredLocations;
    private List<InterviewDetailsDto> interviewsDetails;
}
