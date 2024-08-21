package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.ContactTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTechnologyRepository extends JpaRepository<ContactTechnology,Long> {}
