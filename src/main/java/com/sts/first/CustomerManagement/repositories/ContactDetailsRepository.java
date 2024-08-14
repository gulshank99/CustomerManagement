package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Long> {

//    @Query("SELECT c FROM ContactDetails c " +
//            "WHERE c.contactName LIKE %:query% " +
//            "OR c.emailId LIKE %:query% " +
//            "OR c.companyName LIKE %:query% " +
//            "OR c.designation LIKE %:query% " +
//            "OR c.otherSkills LIKE %:query% " +
//            "OR c.techStack LIKE %:query% " +
//            "OR c.isActive LIKE %:query% " +
//            "OR c.clientCompanyName LIKE %:query% " +
//            "OR c.hiringType LIKE %:query%")
//    List<ContactDetails> searchByKeyword(@Param("query") String query);

}
