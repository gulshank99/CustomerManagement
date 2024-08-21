package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDomainsRepository extends JpaRepository<ContactDomains,Long> {}
