package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ClientJobDomain;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientJobDomainRepository extends JpaRepository<ClientJobDomain,Long> {
    @Query("SELECT COALESCE(MAX(p.clientJobDomainId), 0) FROM ClientJobDomain p")
    Long findMaxId();

    Optional<ContactDomains> findByClientJobJobIdAndDomainDomainId(Long jobId, Long  domainId);

    @Query ("SELECT p.domain.domainDetails from ClientJobDomain p WHERE p.clientJob.jobId = :jobId")
    List<String> findByClientJobs(Long jobId);
}
