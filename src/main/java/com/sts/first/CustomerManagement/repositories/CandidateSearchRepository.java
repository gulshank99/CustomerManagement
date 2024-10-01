package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateSearchRepository extends JpaRepository<ContactDetails, Long> {

    @Query("SELECT DISTINCT c " +
            "FROM ContactDetails c " +
            "LEFT JOIN ContactTechnology t ON t.contactDetails.contactId = c.contactId " +
            "LEFT JOIN ContactDomains d ON d.contactDetails.contactId = c.contactId " +
            "LEFT JOIN ContactPreferredLocation l ON l.contactDetails.contactId = c.contactId " +
            "LEFT JOIN ContactCompany co ON co.contactDetails.contactId = c.contactId " +
            "WHERE (:techRole IS NULL OR c.techRole = :techRole) "+
            "AND (:minExperience IS NULL OR c.totalExperience >= :minExperience) " +
            "AND (:maxExperience IS NULL OR c.totalExperience <= :maxExperience) " +
            "AND (:currentLocation IS NULL OR c.currentLocation.locationDetails = :currentLocation) " +
            "AND (:preferredLocation IS NULL OR l.location.locationDetails IN :preferredLocation) " +
            "AND (:minSalary IS NULL OR c.currentSalary >= :minSalary) " +
            "AND (:maxSalary IS NULL OR c.currentSalary <= :maxSalary) " +
            "AND (:domain IS NULL OR d.domain.domainDetails IN :domain) " +
            "AND (:noticePeriod IS NULL OR c.noticePeriod <= :noticePeriod) "+
            "AND (:technologies IS NULL OR t.technology.technology IN :technologies)"+
            "AND (:companies IS NULL OR co.company.companyName IN :companies)"
    )
    List<ContactDetails> findCandidatesByCriteria(
            @Param("techRole") String techRole,
            @Param("minExperience") Integer minExperience,
            @Param("maxExperience") Integer maxExperience,
            @Param("currentLocation") String currentLocation,
            @Param("preferredLocation") List<String> preferredLocation,
            @Param("minSalary") Integer minSalary,
            @Param("maxSalary") Integer maxSalary,
            @Param("domain") List<String> domain,
            @Param("noticePeriod") Integer noticePeriod,
            @Param("technologies") List<String> technologies,
            @Param("companies") List<String> companies
            );








//    @Query("SELECT DISTINCT c " +
//            "FROM ContactDetails c " +
//            "LEFT JOIN ContactTechnology t ON t.contactDetails.contactId = c.contactId " +
//            "LEFT JOIN ContactDomains d ON d.contactDetails.contactId = c.contactId " +
//            "LEFT JOIN ContactPreferredLocation l ON l.contactDetails.contactId = c.contactId " +
//            "LEFT JOIN ContactCompany co ON co.contactDetails.contactId = c.contactId " +
//            "WHERE (:techRole IS NULL OR c.techRole = :techRole) " +
//            "AND (:minExperience IS NULL OR c.totalExperience >= :minExperience) " +
//            "AND (:maxExperience IS NULL OR c.totalExperience <= :maxExperience) " +
//            "AND (:currentLocation IS NULL OR c.currentLocation.locationDetails = :currentLocation) " +
//            "AND (:preferredLocation IS NULL OR l.location.locationDetails IN :preferredLocation) " +
//            "AND (:minSalary IS NULL OR c.currentSalary >= :minSalary) " +
//            "AND (:maxSalary IS NULL OR c.currentSalary <= :maxSalary) " +
//            "AND (:domain IS NULL OR d.domain.domainDetails = :domain) " +
//            "AND (:noticePeriod IS NULL OR c.noticePeriod <= :noticePeriod) "+
//            "AND (:technologies IS NULL OR t.technology.technology IN :technologies)"+
//            "AND (:companies IS NULL OR co.company.companyName IN :companies)"
//    )
//    List<ContactDetails> findCandidatesByCriteria(
//            @Param("techRole") String techRole,
//            @Param("minExperience") Integer minExperience,
//            @Param("maxExperience") Integer maxExperience,
//            @Param("currentLocation") String currentLocation,
//            @Param("preferredLocation") List<String> preferredLocation,
//            @Param("minSalary") Integer minSalary,
//            @Param("maxSalary") Integer maxSalary,
//            @Param("domain") String domain,
//            @Param("noticePeriod") Integer noticePeriod,
//            @Param("technologies") List<String> technologies,
//            @Param("companies") List<String> companies);


}
