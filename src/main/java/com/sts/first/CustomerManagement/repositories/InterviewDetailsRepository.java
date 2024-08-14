package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.InterviewDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewDetailsRepository extends JpaRepository<InterviewDetails,Long> {

}
