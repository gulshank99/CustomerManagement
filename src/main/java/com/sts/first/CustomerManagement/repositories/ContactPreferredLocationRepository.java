package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactPreferredLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactPreferredLocationRepository extends JpaRepository<ContactPreferredLocation,Long> {

    @Query("SELECT COALESCE(MAX(cp.prefLocationId), 0) FROM ContactPreferredLocation cp")
    Long findMaxId();

    Optional<ContactPreferredLocation> findByContactDetailsContactIdAndLocationLocationId(Long contactId, Long locationId);

    @Query("SELECT p.location.locationDetails from ContactPreferredLocation p WHERE p.contactDetails.contactId = :contactDetails")
    List<String> findByContactDetails(Long contactDetails);

}
