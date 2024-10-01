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
@Table(name = "contact_company", uniqueConstraints = {
@UniqueConstraint(columnNames = {"contact_id", "company_id"})
})
public class ContactCompany {
    @Id
    @Column(name = "contact_company_id")
    private Long contactCompanyId;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MasterCompany company; ;
}
