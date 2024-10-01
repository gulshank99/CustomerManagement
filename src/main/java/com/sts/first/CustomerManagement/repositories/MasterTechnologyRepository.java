package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactTechnology;
import com.sts.first.CustomerManagement.entities.MasterTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MasterTechnologyRepository extends JpaRepository<MasterTechnology,Long> {


    @Query("SELECT COALESCE(MAX(techId), 0) FROM MasterTechnology")
    Long findMaxId();

    Optional<MasterTechnology> findByTechnology(String technology);

}
