package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contacts_technology", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"contact_id", "tech_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTechnology {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_tech_id")
    private Long contactTechId;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "expertise_level")
    private String expertiseLevel;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column(name = "is_secondary")
    private Boolean isSecondary;



    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private ContactDetails contactDetails;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_id")
    private MasterTechnology technology;
}