package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "interview_round",uniqueConstraints = { @UniqueConstraint(columnNames = {"interview_id", "round_number"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRound {

    @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Column(name = "interview_status")
    private String interviewStatus;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private ContactInterviews interview;

//    @ManyToOne
//    @JoinColumn(name = "contact_id")
//    private ContactDetails contactDetails;
}

