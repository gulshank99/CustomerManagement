package com.sts.first.CustomerManagement.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client_job")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "jobId")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClientJob {

    @Id
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "JobCode")
    private String jobCode;

    @Column(name = "JD")
    private String jd;

    @Column(name = "JobTitle")
    private String jobTitle;

    @Column(name = "created_on")
    private LocalDate createdOn;

//    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<ClientJobTech> clientJobTechs;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private MasterClient client;

    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDate.now();
    }

    // Getters and setters
}
