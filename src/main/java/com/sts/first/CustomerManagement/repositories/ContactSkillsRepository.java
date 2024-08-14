package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactSkills;
import com.sts.first.CustomerManagement.entities.InterviewDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactSkillsRepository extends JpaRepository<InterviewDetails,Long> {

}
