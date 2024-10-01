package com.sts.first.CustomerManagement.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterLocation{

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_details", unique = true)
    private String locationDetails;

    @Column(name = "inserted_on")
    private LocalDate insertedOn;

    @PrePersist
    protected void onCreate() {
        this.insertedOn = LocalDate.now();
    }
}