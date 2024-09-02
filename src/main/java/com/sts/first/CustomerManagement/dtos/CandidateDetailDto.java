package com.sts.first.CustomerManagement.dtos;
import lombok.*;

import java.util.List;

@Data
public class CandidateDetailDto {
    private Long contactId;
    private String contactName;
    private String techRole;
    private Integer totalExperience;
    private String currentLocation;
    private Integer currentSalary;
    private Integer noticePeriod;
    private Long primaryNumber;
    private String designation;
    private Long secondaryNumber;
    private String companyName;
    private String emailId;
    private String resume;

    private List<String> domain;
    private List<String> technologies;
    private List<String> preferredLocations;






}
