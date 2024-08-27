package com.sts.first.CustomerManagement.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "contact_interviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInterviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Long interviewId;

    @Column(name = "interview_date")
    private LocalDate interviewDate;

    @Column(name = "interview_status")
    private String interviewStatus;

//    @ManyToOne
//    @JoinColumn(name = "contact_id")
//    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private MasterClient client;

}