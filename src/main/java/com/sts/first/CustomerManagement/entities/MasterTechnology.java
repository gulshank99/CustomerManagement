package com.sts.first.CustomerManagement.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_technology")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private Long techId;

    @Column(name = "technology", nullable = false)
    private String technology;

    @Column(name = "inserted_on")
    private LocalDateTime insertedOn;
}