package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.MasterClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterClientRepository extends JpaRepository<MasterClient,Long> {}
