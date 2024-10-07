package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ClientJob;
import com.sts.first.CustomerManagement.entities.ClientJobTech;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientJobRepository extends JpaRepository<ClientJob, Long> {

    @Query("SELECT COALESCE(MAX(c.jobId), 0) FROM ClientJob c")
    Long findMaxId();

    @Modifying
    @Transactional // Required to perform update operations
    @Query("UPDATE ClientJob c SET c.jd = :jd WHERE c.jobId = :jobId")
     void updateJdByJobId(@Param("jobId") Long jobId, @Param("jd") String jd);


    @Query("SELECT COUNT(c) > 0 FROM ClientJobTech c WHERE c.job.jobTitle = :jobTitle AND c.job.client.clientId = :clientId")
    boolean existsByJobTitleAndClientId(@Param("jobTitle") String jobTitle, @Param("clientId") Long clientId);

//    Optional<ClientJob > findByJobTitleAndClientJobClientId(String jobTitle, Long clientId);
    Optional<ClientJob> findByJobTitleAndClient_ClientId(String jobTitle, Long clientId);

//    Optional<ClientJob> findByJobTitleAndClientId(String jobTitle, Long clientId);

    List<ClientJob> findByClient_ClientId(Long clientId);
    Optional<ClientJob > findByJobCode(String jobCode);


}