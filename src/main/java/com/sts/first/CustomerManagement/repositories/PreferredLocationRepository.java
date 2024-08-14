package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.PreferredLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferredLocationRepository extends JpaRepository<PreferredLocation,Long> {

}
