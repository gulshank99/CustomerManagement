package com.sts.first.CustomerManagement.dtos;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class CandidateDetailDto {
    private Long contactId;
    private String firstName;
    private String lastName;
    private String designation;
    private String maritalStatus;
    private String techRole;
    private Integer totalExperience;
    private String currentLocation;
    private Integer currentSalary;
    private Integer noticePeriod;
    private String primaryNumber;
    private String secondaryNumber;
    private String companyName;
    private String emailId;
    private String resume;
    private Boolean isActive;
    private Boolean isInterviewDone;
    private String gender;
    private String highestEducation;
    private String hiringType;



    private List<String> domain;
    private List<String> technologies;
    private List<String> preferredLocations;
    private List<String> companies;












}
