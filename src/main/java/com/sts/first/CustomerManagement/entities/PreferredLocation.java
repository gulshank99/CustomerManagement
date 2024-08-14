package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "preferred_location")
public class PreferredLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferredLocationId;

    @ManyToOne
    @JoinColumn(name = "contactId")
    private ContactDetails contactDetails;


    private String locationDetails;


}
