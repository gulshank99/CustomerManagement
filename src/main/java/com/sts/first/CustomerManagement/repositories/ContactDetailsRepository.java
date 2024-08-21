package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Long> {

    // Search by Contact Name
    List<ContactDetails> findByContactNameContainingIgnoreCase(String contactName);
    List<ContactDetails> findByPrimaryNumberOrSecondaryNumber(Long primaryNumber, Long secondaryNumber);
    // Search by Email ID
    List<ContactDetails> findByEmailIdContainingIgnoreCase(String emailId);

    // Search by Designation
    List<ContactDetails> findByDesignationContainingIgnoreCase(String designation);

    // Search by Domain
    List<ContactDetails> findByDomain_DomainDetailsContainingIgnoreCase(String domainDetails);

    // Search by Technology
    List<ContactDetails> findByTechnology_TechnologyContainingIgnoreCase(String technology);

    // Search by Location
    List<ContactDetails> findByPreferredLocation_LocationDetailsContainingIgnoreCase(String locationDetails);

}
