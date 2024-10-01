package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ClientJob;
import com.sts.first.CustomerManagement.entities.ClientJobTech;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientJobRepository extends JpaRepository<ClientJob, Long> {

    @Query("SELECT COALESCE(MAX(c.jobId), 0) FROM ClientJob c")
    Long findMaxId();

    @Modifying
    @Transactional // Required to perform update operations
    @Query("UPDATE ClientJob c SET c.jd = :jd WHERE c.jobId = :jobId")
     void updateJdByJobId(@Param("jobId") Long jobId, @Param("jd") String jd);


}