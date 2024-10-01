package com.sts.first.CustomerManagement.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SearchCriteriaDto {
    private String techRole;
    private Integer minExperience;
    private Integer maxExperience;
    private String currentLocation;
    private Integer minSalary;
    private Integer maxSalary;
    private Integer noticePeriod;

    private List<String> preferredLocation;
    private List<String> domain;
    private List<String> technologies;
    private List<String> companies;
}


