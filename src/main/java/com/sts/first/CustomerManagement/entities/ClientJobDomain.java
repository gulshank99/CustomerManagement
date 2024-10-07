package com.sts.first.CustomerManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "client_job_domain",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"job_id", "domain_id"})
})
public class ClientJobDomain {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_job_domain_id")
    private Long clientJobDomainId;

//    @Column(name = "is_primary_domain")
//    private Boolean isPrimaryDomain;
//
//    @Column(name = "is_secondary_domain")
//    private Boolean isSecondaryDomain;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private ClientJob clientJob;
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    @JsonIgnore
    private MasterDomain domain;
}
