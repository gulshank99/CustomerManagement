package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactCompany;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.MasterDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactCompanyRepository extends JpaRepository<ContactCompany,Long> {
    @Query("SELECT COALESCE(MAX(p.contactDomainId), 0) FROM ContactDomains p")
    Long findMaxId();


    Optional<ContactCompany> findByContactDetailsContactIdAndCompanyCompanyId(Long contactId, Long companyId);


    @Query ("SELECT p.company.companyName  from ContactCompany  p WHERE p.contactDetails.contactId = :contactDetails")
    List<String> findByContactDetails(Long contactDetails);

//    @Query("SELECT p.technology.technology from ContactTechnology p WHERE p.contactDetails.contactId = :contactDetails")
//    List<String> findByContactDetails(Long contactDetails);



}
