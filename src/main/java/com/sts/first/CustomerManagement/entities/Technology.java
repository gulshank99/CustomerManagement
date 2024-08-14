package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "technology")
public class Technology {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techId;

    private String technology;
//    private LocalDateTime dateTimeInserted;

    @OneToMany(mappedBy = "technology", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContactSkills> contactsSkills;
}
