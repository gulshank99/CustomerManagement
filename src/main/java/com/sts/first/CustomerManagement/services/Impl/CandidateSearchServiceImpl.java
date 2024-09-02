package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.CandidateDetailDto;
import com.sts.first.CustomerManagement.dtos.SearchCriteriaDto;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.exceptions.BadApiRequestException;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.*;
import com.sts.first.CustomerManagement.services.CandidateSearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateSearchServiceImpl implements CandidateSearchService {

    @Autowired
    private CandidateSearchRepository candidateSearchRepository;

    @Autowired
    private ContactTechnologyRepository contactTechnologyRepository;
    @Autowired
    private ContactDomainsRepository contactDomainsRepository;
    @Autowired
    private ContactPreferredLocationRepository contactPreferredLocationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CandidateDetailDto> searchCandidates(SearchCriteriaDto searchCriteriaDto) {
        if (isSearchCriteriaEmpty(searchCriteriaDto)) {
            throw new BadApiRequestException("Please provide some fields to search.");
        }

        List<ContactDetails> candidates = candidateSearchRepository.findCandidatesByCriteria(
                searchCriteriaDto.getTechRole(),
                searchCriteriaDto.getMinExperience(),
                searchCriteriaDto.getMaxExperience(),
                searchCriteriaDto.getCurrentLocation(),
                searchCriteriaDto.getPreferredLocation(),
                searchCriteriaDto.getMinSalary(),
                searchCriteriaDto.getMaxSalary(),
                searchCriteriaDto.getDomain(),
                searchCriteriaDto.getNoticePeriod(),
                searchCriteriaDto.getTechnologies()
        );

        List<CandidateDetailDto> candidateDetailDtos = new ArrayList<>();

        for (ContactDetails candidate : candidates) {
            CandidateDetailDto candidateDetailDto = modelMapper.map(candidate, CandidateDetailDto.class);

            List<String> domain = contactDomainsRepository.findByContactDetails(candidate.getContactId());
            List<String> technologies=contactTechnologyRepository.findByContactDetails(candidate.getContactId());
            List<String> preferredLocations=contactPreferredLocationRepository.findByContactDetails(candidate.getContactId());

            candidateDetailDto.setDomain(domain);
            candidateDetailDto.setTechnologies(technologies);
            candidateDetailDto.setPreferredLocations(preferredLocations);
            candidateDetailDtos.add(candidateDetailDto);
        }
         if(candidateDetailDtos.isEmpty())  throw new ResourceNotFoundException("No Relevant Candidate Found");
        return candidateDetailDtos;

    }

    private boolean isSearchCriteriaEmpty(SearchCriteriaDto searchCriteriaDto) {
        return searchCriteriaDto.getTechRole() == null &&
                searchCriteriaDto.getMinExperience() == null &&
                searchCriteriaDto.getMaxExperience() == null &&
                searchCriteriaDto.getCurrentLocation() == null &&
                (searchCriteriaDto.getPreferredLocation() == null || searchCriteriaDto.getPreferredLocation().isEmpty()) &&
                searchCriteriaDto.getMinSalary() == null &&
                searchCriteriaDto.getMaxSalary() == null &&
                searchCriteriaDto.getDomain() == null &&
                searchCriteriaDto.getNoticePeriod() == null &&
                (searchCriteriaDto.getTechnologies() == null || searchCriteriaDto.getTechnologies().isEmpty());
    }


}
