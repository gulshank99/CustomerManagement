package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_domain")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id")
    private Long domainId;

    @Column(name = "domain_details", nullable = false)
    private String domainDetails;

    @Column(name = "inserted_on")
    private LocalDate insertedOn;


    @PrePersist
    protected void onCreate() {
        this.insertedOn = LocalDate.now();
    }


}
