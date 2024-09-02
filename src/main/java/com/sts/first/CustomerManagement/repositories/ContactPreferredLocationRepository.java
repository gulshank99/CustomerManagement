package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactPreferredLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactPreferredLocationRepository extends JpaRepository<ContactPreferredLocation,Long> {

    @Query("SELECT p.location.locationDetails from ContactPreferredLocation p WHERE p.contactDetails.contactId = :contactDetails")
    List<String> findByContactDetails(Long contactDetails);

}
