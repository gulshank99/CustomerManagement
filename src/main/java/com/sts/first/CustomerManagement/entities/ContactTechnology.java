package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contacts_technology")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "expertise_level")
    private String expertiseLevel;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column(name = "is_secondary")
    private Boolean isSecondary;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name = "tech_id")
    private MasterTechnology technology;
}