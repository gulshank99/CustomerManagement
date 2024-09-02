package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.ContactTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactTechnologyRepository extends JpaRepository<ContactTechnology,Long> {
    @Query("SELECT p.technology.technology from ContactTechnology p WHERE p.contactDetails.contactId = :contactDetails")
    List<String> findByContactDetails(Long contactDetails);
}
