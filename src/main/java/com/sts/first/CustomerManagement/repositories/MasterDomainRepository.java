package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.MasterDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterDomainRepository extends JpaRepository<MasterDomain,Long> {}
