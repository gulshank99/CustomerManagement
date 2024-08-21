package com.sts.first.CustomerManagement.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterLocation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_details", nullable = false)
    private String locationDetails;

    @Column(name = "inserted_on")
    private LocalDateTime insertedOn;
}