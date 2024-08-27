package com.sts.first.CustomerManagement.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "address")
    private String address;

    @Column(name = "hr_contact_person")
    private String hrContactPerson;

    @Column(name = "technical_person")
    private String technicalPerson;

    @Column(name = "primary_mobile")
    private Long primaryMobile;

    @Column(name = "secondary_number")
    private Long secondaryNumber;

    @Column(name = "inserted_on")
    private LocalDate insertedOn;


    @PrePersist
    protected void onCreate() {
        this.insertedOn = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "location_id")
    private MasterLocation location;
}