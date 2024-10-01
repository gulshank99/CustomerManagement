package com.sts.first.CustomerManagement.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "clientId")
@Table(name = "master_client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterClient {

    @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name", nullable = false, unique = true)
    private String clientName;

    @Column(name = "client_ho")
    private String clientHo;


    @Column(name = "inserted_on")
    private LocalDate insertedOn;

//    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<ClientLocation> clientLocations;

//    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<ClientJob> clientJobs;

    @PrePersist
    protected void onCreate() {
        this.insertedOn = LocalDate.now();
    }

}