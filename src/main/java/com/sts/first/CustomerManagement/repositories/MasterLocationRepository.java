package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MasterLocationRepository extends JpaRepository<MasterLocation,Long> {

    @Query("SELECT COALESCE(MAX(ml.locationId), 0) FROM MasterLocation ml")
    Long findMaxId();

    Optional<MasterLocation> findByLocationDetails(String locationDetails);
}
