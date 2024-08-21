package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
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

@Entity
@Table(name = "contacts_details")
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "primary_number", nullable = false)
    private Long primaryNumber;

    @Column(name = "designation")
    private String designation;

    @Column(name = "secondary_number")
    private Long secondaryNumber;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "resume")
    private String resume;

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Lob
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

    @Column(name = "highest_education")
    private String highestEducation;

    @Column(name = "hiring_type")
    private String hiringType;

    @Column(name = "tech_stack")
    private String techStack;

    @Column(name = "inserted_on")
    private LocalDate insertedOn;


    @PrePersist
    protected void onCreate() {
        this.insertedOn = LocalDate.now();
    }

    // Relationships
    @ManyToOne
    @JoinColumn(name = "domain_id")
    private MasterDomain domain;

    @ManyToOne
    @JoinColumn(name = "tech_id")
    private MasterTechnology technology;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private MasterLocation preferredLocation;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private ContactInterviews interview;

    @ManyToOne
    @JoinColumn(name = "current_location_id")
    private MasterLocation currentLocation;




}


