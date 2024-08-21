package com.sts.first.CustomerManagement.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_domains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDomains {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_primary_domain")
    private Boolean isPrimaryDomain;

    @Column(name = "is_secondary_domain")
    private Boolean isSecondaryDomain;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private MasterDomain domain;
}