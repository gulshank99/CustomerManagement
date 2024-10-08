package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_preferred_location",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"contact_id","location_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPreferredLocation {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pref_location_id")
    private Long prefLocationId;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private MasterLocation location;
}
