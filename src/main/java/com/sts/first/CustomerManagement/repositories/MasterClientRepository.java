package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.MasterClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MasterClientRepository extends JpaRepository<MasterClient,Long> {

    @Query("SELECT COALESCE(MAX(mc.clientId), 0) FROM MasterClient mc")
    Long findMaxId();


    Optional<MasterClient> findByClientName(String clientName);

    // Find by primaryMobile to ensure uniqueness
//    Optional<MasterClient> findByPrimaryMobile(String primaryMobile);

    // Find by secondaryNumber to ensure uniqueness
//    Optional<MasterClient> findBySecondaryNumber(String secondaryNumber);
}
