package com.sts.first.CustomerManagement.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_location")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "clientLocationId")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClientLocation {

    @Id
    @Column(name = "client_location_id")
    private Long clientLocationId;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "address1")
    private String Address1;

    @Column(name = "hr_contact_person")
    private String hrContactPerson;

    @Column(name = "technical_person")
    private String technicalPerson;

    @Column(name = "hr_mobile_number", unique = true)
    private String hrMobileNumber;

    @Column(name = "company_landline", unique = true)
    private String companyLandline;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnore
    private MasterLocation state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private MasterLocation cityId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private MasterClient client;



//    @ManyToOne
//    @JoinColumn(name = "location_id")
//    private MasterLocation location;
//
//    @Column(name = "address")
//    private String address;


//    private String countryCode;

//    @Column(name = "primary_mobile", unique = true)
//    private String primaryMobile;
//
//    @Column(name = "secondary_number", unique = true)
//    private String secondaryNumber;







}
