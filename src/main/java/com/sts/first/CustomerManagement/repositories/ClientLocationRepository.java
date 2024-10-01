package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ClientLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ClientLocationRepository extends JpaRepository<ClientLocation, Long> {
    @Query("SELECT COALESCE(MAX(c.clientLocationId), 0) FROM ClientLocation c")
    Long findMaxId();

//     Find by primaryMobile to ensure uniqueness
    Optional<ClientLocation> findByHrMobileNumber(String hrMobileNumber);

//     Find by secondaryNumber to ensure uniqueness
    Optional<ClientLocation> findByCompanyLandline(String companyLandline);
}