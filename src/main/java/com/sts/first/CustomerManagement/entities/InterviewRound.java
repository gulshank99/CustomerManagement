package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "interview_round")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id")
    private Long roundId;

    @Column(name = "round_number")
    private Integer roundNumber;

    @Column(name = "round_date")
    private LocalDate roundDate;

    @Column(name = "interviewer_name")
    private String interviewerName;

    @Column(name = "technology_interviewed")
    private String technologyInterviewed;

    @Column(name = "tech_rating")
    private Integer techRating;

    @Column(name = "softskills_rating")
    private Integer softskillsRating;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private ContactInterviews interview;

//    @ManyToOne
//    @JoinColumn(name = "contact_id")
//    private ContactDetails contactDetails;
}

