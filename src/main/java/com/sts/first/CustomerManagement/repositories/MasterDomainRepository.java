package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.MasterDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MasterDomainRepository extends JpaRepository<MasterDomain,Long> {
   @Query("SELECT COALESCE(MAX(md.domainId), 0) FROM MasterDomain md")
    Long findMaxId();

    Optional<MasterDomain> findByDomainDetails(String domainDetails);
}
