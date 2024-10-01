package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.persistence.Column;
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

  @NotBlank(message = "First Name is required !!", groups = CreateValidation.class )
  @Size(min = 3,max = 30,message = "Invalid Name !!", groups = {CreateValidation.class, UpdateValidation.class})
  @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name  must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
  private String firstName;

  @NotBlank(message = "Last Name is required !!", groups = CreateValidation.class )
  @Size(min = 3,max = 30,message = "Invalid Name !!", groups = {CreateValidation.class, UpdateValidation.class})
  private String LastName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dob;

//  @NotBlank(message = "Phone number is required !!", groups = CreateValidation.class)
//  @Pattern(regexp = "^(\\+?\\d{1,3}|\\d{1,4})$", message = "Invalid country code", groups = {CreateValidation.class, UpdateValidation.class})
//  private String countryCode;

  @NotBlank(message = "Primary Phone number is required !!", groups = CreateValidation.class)
//  @Min(value = 1000, message = "Phone number must be at least 4 digits long.", groups = {CreateValidation.class, UpdateValidation.class})
//  @Digits(integer = 11, fraction = 0, message = "Phone number must be within 15 digits. !!", groups = {CreateValidation.class, UpdateValidation.class})
  @Pattern(regexp = "^(?:\\+?\\d{1,3}[- ]?)?\\d{8,12}$", message = "Mention Phone number with Country Code (Space not allowed).", groups = {CreateValidation.class, UpdateValidation.class})
  private String primaryNumber;


  private String address1;

  private String AddressLocality;


//  @Min(value = 1000, message = "Phone number must be at least 4 digits long.", groups = {CreateValidation.class, UpdateValidation.class})
//  @Digits(integer = 11, fraction = 0, message = "Phone number must be within 11 digits. !!", groups = {CreateValidation.class, UpdateValidation.class})
  @NotBlank(message = "Secondary Phone number is required !!", groups = CreateValidation.class)
  @Pattern(regexp = "^(?:\\+?\\d{1,3}[- ]?)?\\d{8,12}$", message = "Mention Phone number with Country Code (Space not allowed).", groups = {CreateValidation.class, UpdateValidation.class})
  private String secondaryNumber;

  @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,4}$", message = "Invalid Email Type !!", groups = {CreateValidation.class, UpdateValidation.class})
   private String emailId;

  @NotNull(message = "Designation is required !!", groups = CreateValidation.class)
  @Size(min = 3,max = 50,message = "Invalid Designation !!", groups = {CreateValidation.class, UpdateValidation.class})
  private String designation;

  //@Pattern(regexp = "^[a-zA-Z]+$", message = "Company Name must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
  private String companyName;

  private String resume;

  @NotNull(message = "Total Experience is required !!", groups = CreateValidation.class)
  @Digits(integer = 3, fraction = 0, message = "Experience must be a whole number !!", groups = {CreateValidation.class, UpdateValidation.class})
  private Long totalExperience;

  private String image;

  @NotNull(message = "Active Status is required !!", groups = CreateValidation.class)
  //@AssertTrue(message = "IsActive flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
  private Boolean isActive;

  @NotNull(message = "Active Status is required !!", groups = CreateValidation.class)
 // @AssertTrue(message = "IsInterviewDone flag must be true or false", groups = {CreateValidation.class, UpdateValidation.class})
  private Boolean isInterviewDone;

  @NotNull(message = "Current Salary is required !!", groups = CreateValidation.class)
  @Positive(message = "Salary must be a positive number !!", groups = {CreateValidation.class, UpdateValidation.class})
  private Double currentSalary;

  @NotBlank(message = "Gender is required !!", groups = CreateValidation.class )
  @Size(min = 3,max = 20,message = "Invalid Gender !!", groups = {CreateValidation.class, UpdateValidation.class})
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Gender must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
  private String gender;

  @NotBlank(message = "Education is required !!", groups = CreateValidation.class )
  @Size(min = 3,max = 30,message = "Invalid Education !!", groups = {CreateValidation.class, UpdateValidation.class})
//  @Pattern(regexp = "^[a-zA-Z]+$", message = "Education must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
  private String highestEducation;

//  @Pattern(regexp = "^[a-zA-Z]+$", message = "Technology must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
  private String hiringType;


  private Long pinCode;

  @NotBlank(message = "Marital Status is required !!", groups = CreateValidation.class )
  @Size(min = 3,max = 10,message = "Invalid Marital Status !!", groups = {CreateValidation.class, UpdateValidation.class})
  private String maritalStatus;


  @NotBlank(message = "Tech Role required !!", groups = CreateValidation.class)
  @Size(min = 3,max = 30,message = "Invalid Tech Role !!", groups = {CreateValidation.class, UpdateValidation.class})
  private String techRole;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate insertedOn;

  @NotNull(message = "Notice period is required !!", groups = CreateValidation.class)
  private Long noticePeriod;

  @NotNull(message = "Tech Role is required !!", groups = {CreateValidation.class})
  private MasterLocationDto currentLocation;


//    private Long contactId;
//    @NotNull(message = "Contact Name is required !!")
//    @NotBlank(message = "Contact Name is required !!")
//    private String contactName;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate dob;
//
//  @NotNull(message = "Phone number is required !!")
//  @Pattern(regexp = "^(\\+?\\d{1,3}|\\d{1,4})$", message = "Invalid country code")
//  private String countryCode;
//
//    @NotNull(message = "Primary Phone number is required !!")
//    @Min(value = 1000, message = "Phone number must be at least 4 digits long.")
//    @Digits(integer = 11, fraction = 0, message = "Phone number must be within 15 digits. !!")
//    private Long primaryNumber;
//    private String designation;
//  @Min(value = 1000, message = "Phone number must be at least 4 digits long.")
//    @Digits(integer = 11, fraction = 0, message = "Phone number must be within 11 digits. !!")
//    private Long secondaryNumber;
//    private String companyName;
//    private String resume;
//    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,4}$",message = "Invalid Email Type !!")
//    @NotBlank(message = "Email is required !!")
//    @NotNull(message = "Email Id is required !!")
//    private String emailId;
//    @NotNull(message = "Experience is required !!")
//    private Long totalExperience;
//    private String image;
//    private Boolean isActive;
//    private Boolean isInterviewDone;
//    private Double currentSalary;
//    @NotBlank(message = "Gender is required !!")
//    private String gender;
//    @NotBlank(message = "Education is required !!")
//    private String highestEducation;
//    private String hiringType;
//    @NotBlank(message="Tech Role required !!")
//    @NotNull(message = "Tech Role is required !!")
//    private String techRole;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate insertedOn;
//    @NotNull(message = "Notice period is required !!")
//    private Long noticePeriod;
////    private ContactInterviewsDto interview;
//    @NotNull(message = "Tech Role is required !!")
//    private MasterLocationDto currentLocation;
//


}
