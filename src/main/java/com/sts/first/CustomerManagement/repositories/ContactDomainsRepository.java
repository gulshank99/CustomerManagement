package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactDomainsRepository extends JpaRepository<ContactDomains,Long> {

   @Query("SELECT COALESCE(MAX(p.contactDomainId), 0) FROM ContactDomains p")
   Long findMaxId();


   Optional<ContactDomains> findByContactDetailsContactIdAndDomainDomainId(Long contactId, Long domainId);


   @Query ("SELECT p.domain.domainDetails from ContactDomains p WHERE p.contactDetails.contactId = :contactDetails")
   List<String> findByContactDetails(Long contactDetails);
}
