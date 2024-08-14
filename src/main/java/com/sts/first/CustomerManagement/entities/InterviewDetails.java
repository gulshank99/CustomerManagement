package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "interview_details")
public class InterviewDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @ManyToOne
    @JoinColumn(name = "contactId")
    private ContactDetails contactDetails;

    private String interviewedBy;
    private Float rating;
    private LocalDateTime interviewedOnDate;
    @Column(name = "interview_status")
    private String interviewStatus;
    private String remarks;




}
