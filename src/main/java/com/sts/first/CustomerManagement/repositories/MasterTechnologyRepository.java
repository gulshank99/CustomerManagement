package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactTechnology;
import com.sts.first.CustomerManagement.entities.MasterTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterTechnologyRepository extends JpaRepository<MasterTechnology,Long> {

}
