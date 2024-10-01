package com.sts.first.CustomerManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "master_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterCompany {
    @Id
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;
}
