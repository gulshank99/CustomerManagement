package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "Contact Name is required !!")
    @NotBlank(message = "Contact Name is required !!")
    private String contactName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @NotNull(message = "Primary Phone number is required !!")
    @Min(value = 1000000000L, message = "Phone number must be exactly 10 digits.")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits. !!")
    private Long primaryNumber;
    private String designation;
    @Min(value = 1000000000L, message = "Phone number must be exactly 10 digits.")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits. !!")
    private Long secondaryNumber;
    private String companyName;
    private String resume;
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,3}$",message = "Invalid Email Type !!")
    @NotBlank(message = "Email is required !!")
    @NotNull(message = "Email Id is required !!")
    private String emailId;
    @NotNull(message = "Experience is required !!")
    private Long totalExperience;
    private String image;
    private Boolean isActive;
    private Boolean isInterviewDone;
    private Double currentSalary;
    @NotBlank(message = "Gender is required !!")
    private String gender;
    @NotBlank(message = "Education is required !!")
    private String highestEducation;
    private String hiringType;
    @NotBlank(message="Tech Role required !!")
    @NotNull(message = "Tech Role is required !!")
    private String techRole;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate insertedOn;
    @NotNull(message = "Notice period is required !!")
    private Long noticePeriod;

//    private ContactInterviewsDto interview;
    @NotNull(message = "Tech Role is required !!")
    private MasterLocationDto currentLocation;
}
