package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
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
    private String contactName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private Long primaryNumber;
    private String designation;
    private Long secondaryNumber;
    private String companyName;
    private String resume;
    private String emailId;
    private String image;
    private Boolean isActive;
    private Boolean isInterviewDone;
    private Double currentSalary;
    private String gender;
    private String highestEducation;
    private String hiringType;
    private String techStack;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate insertedOn;

    private MasterDomainDto domain;
    private MasterTechnologyDto technology;
    private MasterLocationDto preferredLocation;
    private ContactInterviewsDto interview;
    private MasterLocationDto currentLocation;
}
