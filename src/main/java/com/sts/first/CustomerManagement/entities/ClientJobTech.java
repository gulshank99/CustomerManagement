package com.sts.first.CustomerManagement.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_job_tech",uniqueConstraints = { @UniqueConstraint(columnNames = {"job_id", "tech_id"})})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "jobCodeTechId")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClientJobTech {

    @Id
    @Column(name = "Job_code_tech_Id")
    private Long jobCodeTechId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @JsonIgnore
    private ClientJob job;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_id")
    @JsonIgnore
    private MasterTechnology technology;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    @JsonIgnore
    private MasterDomain domain;

    // Getters and setters
}
