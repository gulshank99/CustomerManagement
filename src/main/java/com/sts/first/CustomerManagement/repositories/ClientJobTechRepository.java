package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ClientJobTech;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import com.sts.first.CustomerManagement.entities.InterviewRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientJobTechRepository extends JpaRepository<ClientJobTech, Long> {

    @Query("SELECT COALESCE(MAX(c.jobCodeTechId), 0) FROM ClientJobTech c")
    Long findMaxId();
    Optional<ClientJobTech> findByJob_JobIdAndTechnology_TechId(Long jobId, Long techId);


}