package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "contacts_skills")
public class ContactSkills {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name = "tech_id", nullable = false)
    private Technology technology;

    private Integer experience;
    private String expertiseLevel;

}
