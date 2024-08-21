package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterLocationRepository extends JpaRepository<MasterLocation,Long> {}
