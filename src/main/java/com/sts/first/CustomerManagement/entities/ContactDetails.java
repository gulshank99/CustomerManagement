package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "contacts_details")
public class ContactDetails {
    @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "address1")
    private String address1;

    @Column(name = "addressLocality")
    private String AddressLocality;

    @Column(name = "pin_code")
    private Long pinCode;

//    private String countryCode;

    @Column(name = "primary_number", unique = true)
    private String primaryNumber;

    @Column(name = "designation")
    private String designation;

    @Column(name = "secondary_number",unique = true)
    private String secondaryNumber;

    @Column(name = "company_name")
    private String companyName;

    private Long totalExperience;

    @Column(name = "resume")
    private String resume;

    @Column(name = "email_id", unique = true)
    private String emailId;

//    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_interview_done")
    private Boolean isInterviewDone;

    @Column(name = "current_salary")
    private Double currentSalary;

    @Column(name = "gender")
    private String gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "highest_education")
    private String highestEducation;

    @Column(name = "hiring_type")
    private String hiringType;

    @Column(name = "tech_Role")
    private String techRole;

    @Column(name = "inserted_on")
    private LocalDate insertedOn;

    private Long noticePeriod;


    @PrePersist
    protected void onCreate() {
        this.insertedOn = LocalDate.now();
    }

    // Relationships
    @ManyToOne
    @JoinColumn(name = "current_location_id")
    private MasterLocation currentLocation;

//    @ManyToOne
//    @JoinColumn(name = "domain_id")
//    private MasterDomain domain;

//    @ManyToOne
//    @JoinColumn(name = "tech_id")
//    private MasterTechnology technology;
//
//    @ManyToOne
//    @JoinColumn(name = "location_id")
//    private MasterLocation preferredLocation;

//    @ManyToOne
//    @JoinColumn(name = "interview_id")
//    private ContactInterviews interview;






}


