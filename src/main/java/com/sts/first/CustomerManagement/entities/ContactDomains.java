package com.sts.first.CustomerManagement.entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "contact_domains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDomains {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_domain_id")
    private Long contactDomainId;

    @Column(name = "is_primary_domain")
    private Boolean isPrimaryDomain;

    @Column(name = "is_secondary_domain")
    private Boolean isSecondaryDomain;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private ContactDetails contactDetails;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    private MasterDomain domain;
}