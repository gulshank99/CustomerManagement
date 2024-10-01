package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Long> {

    @Query("SELECT COALESCE(MAX(cd.contactId), 0) FROM ContactDetails cd")
    Long findMaxId();

    // Search by Contact Name

    @Modifying
    @Transactional // Required to perform update operations
    @Query("UPDATE ContactDetails c SET c.resume = :resume WHERE c.contactId = :contactId")
    void updateResumeByContactId(@Param("contactId") Long contactId, @Param("resume") String resume);

    @Modifying
    @Transactional // Required to perform update operations
    @Query("UPDATE ContactDetails c SET c.image = :image WHERE c.contactId = :contactId")
    void updateImageByContactId(@Param("contactId") Long contactId, @Param("image") String image);

    Optional<ContactDetails> findByPrimaryNumber(String primaryNumber);
    Optional<ContactDetails> findBySecondaryNumber(String secondaryNumber);
    Optional<ContactDetails> findByEmailId(String emailId);

    List<ContactDetails> findByFirstNameContainingIgnoreCase(String firstName);
    List<ContactDetails> findByLastNameContainingIgnoreCase(String lastName);
    List<ContactDetails> findByPrimaryNumberOrSecondaryNumber(String primaryNumber, String secondaryNumber);
    // Search by Email ID
    List<ContactDetails> findByEmailIdContainingIgnoreCase(String emailId);

    // Search by Designation
    List<ContactDetails> findByDesignationContainingIgnoreCase(String designation);

//    void updateContactField(Long contactId, String resume, String resumeUrl);

    // Search by Domain
//    List<ContactDetails> findByDomain_DomainDetailsContainingIgnoreCase(String domainDetails);

//    // Search by Technology
//    List<ContactDetails> findByTechnology_TechnologyContainingIgnoreCase(String technology);

    // Search by Location
//    List<ContactDetails> findByPreferredLocation_LocationDetailsContainingIgnoreCase(String locationDetails);

}
