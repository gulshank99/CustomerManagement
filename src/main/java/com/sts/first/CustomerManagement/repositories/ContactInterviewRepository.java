package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInterviewRepository extends JpaRepository<ContactInterviews,Long> {}
