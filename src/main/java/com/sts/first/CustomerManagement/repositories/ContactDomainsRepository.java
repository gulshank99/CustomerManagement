package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactDomainsRepository extends JpaRepository<ContactDomains,Long> {

   @Query ("SELECT p.domain.domainDetails from ContactDomains p WHERE p.contactDetails.contactId = :contactDetails")
   List<String> findByContactDetails(Long contactDetails);
}
