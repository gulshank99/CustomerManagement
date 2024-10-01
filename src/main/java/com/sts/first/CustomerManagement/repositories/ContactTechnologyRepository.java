package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.ContactTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactTechnologyRepository extends JpaRepository<ContactTechnology,Long> {

    @Query("SELECT COALESCE(MAX(p.contactTechId), 0) FROM ContactTechnology p")
    Long findMaxId();

    Optional<ContactTechnology> findByContactDetailsContactIdAndTechnologyTechId(Long contactId, Long techId);
    @Query("SELECT p.technology.technology from ContactTechnology p WHERE p.contactDetails.contactId = :contactDetails")
    List<String> findByContactDetails(Long contactDetails);
}
