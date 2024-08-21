package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_preferred_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPreferredLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private MasterLocation location;
}
