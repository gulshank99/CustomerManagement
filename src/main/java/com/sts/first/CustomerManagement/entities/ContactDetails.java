package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "primary_number", nullable = false, unique = true)
    private Long primaryNumber;

    @Column(name = "designation")
    private String designation;

    @Column(name = "secondary_number", unique = true)
    private Long secondaryNumber;

    @Column(name = "other_skills")
    private String otherSkills;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "resume", unique = true)
    private String resume;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "current_salary")
    private Double currentSalary;

    @Column(name = "hiring_type")
    private String hiringType;

    private String linkdinId;
    private String clientCompanyName;

    @Column(name = "date_time_inserted")
    private LocalDateTime dateTimeInserted;

    @Column(name = "techStack")
    private String techStack;



    @OneToMany(mappedBy = "contactDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContactSkills> skills;

    @OneToMany(mappedBy = "contactDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PreferredLocation> preferredLocations;

    @OneToMany(mappedBy = "contactDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InterviewDetails> interviewsDetails;

//    @Column(name = "preferred_location_id")
//    private Long preferredLocationId;
//
//    @Column(name = "interview_id")
//    private Long interviewId;




}


