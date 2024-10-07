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

    @Column(name = "JobCode", unique = true)
    private String jobCode;

    @Column(name = "JD")
    private String jd;

    @Column(name = "JobTitle")
    private String jobTitle;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "is_job_active")
    private String isJobActive;

    @Column(name = "post_created_on")
    private LocalDate postCreatedOn;

    @Column(name = "job_post_type")
    private String jobPostType;

    @Column(name = "inserted_by")
    private String insertedBy;

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
