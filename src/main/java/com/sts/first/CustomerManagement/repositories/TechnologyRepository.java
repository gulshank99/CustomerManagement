package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology,Long> {

}
