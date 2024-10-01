package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContactInterviewRepository extends JpaRepository<ContactInterviews,Long> {

    Optional<ContactInterviews> findByContactDetailsContactIdAndClientClientId(Long contactId, Long clientId);
    @Query("SELECT COALESCE(MAX(ci.interviewId), 0) FROM ContactInterviews ci")
    Long findMaxId();
}
