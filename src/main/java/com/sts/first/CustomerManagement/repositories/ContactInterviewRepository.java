package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactInterviewRepository extends JpaRepository<ContactInterviews,Long> {

//    Optional<ContactInterviews> findByContactDetailsContactIdAndClientClientId(Long contactId, Long clientId);
   List<ContactInterviews> findByClientJobJobId(Long jobId);

   List<ContactInterviews> findByContactDetailsContactId(Long contactId);

//    Optional<ContactInterviews> findByContactDetailsContactIdAndClientJobJobId(Long contactId, Long jobId);

    List<ContactInterviews> findByContactDetailsContactIdAndClientJobJobId(Long contactId, Long jobId);
//    List<ContactInterviews> findByContactDetailsContactIdAndMasterClientClientId(Long contactId, Long clientId);
    List<ContactInterviews> findByContactDetailsContactIdAndClientJobClientClientId(Long contactId, Long clientId);

    @Query("SELECT COALESCE(MAX(ci.interviewId), 0) FROM ContactInterviews ci")
    Long findMaxId();
}
