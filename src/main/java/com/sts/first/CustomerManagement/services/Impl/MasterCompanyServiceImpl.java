package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.dtos.ContactDomainsDto;
import com.sts.first.CustomerManagement.dtos.MasterCompanyDto;
import com.sts.first.CustomerManagement.dtos.MasterDomainDto;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.MasterCompany;
import com.sts.first.CustomerManagement.entities.MasterDomain;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.*;
import com.sts.first.CustomerManagement.services.MasterCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MasterCompanyServiceImpl implements MasterCompanyService {

    @Autowired
    private MasterCompanyRepository  masterCompanyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MasterCompanyDto createCompany(MasterCompanyDto companyDto) {

        Long maxId = masterCompanyRepository.findMaxId();
        Long newId = maxId + 1;
        companyDto.setCompanyId(newId);

        Optional<MasterCompany> existingCompany = masterCompanyRepository.findByCompanyName(companyDto.getCompanyName());
        if (existingCompany.isPresent()) {
            throw new IllegalArgumentException("Company details already exist.");
        }
        MasterCompany company = modelMapper.map(companyDto, MasterCompany.class);
        MasterCompany saveCompany = masterCompanyRepository.save(company);
        return modelMapper.map(saveCompany, MasterCompanyDto.class);
    }

    @Override
    public MasterCompanyDto updateCompany(Long contactId, MasterCompanyDto companyDto) {
        MasterCompany company = masterCompanyRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Company  not found with id: " +  contactId));

        Optional<MasterCompany> existingCompany = masterCompanyRepository.findByCompanyName(companyDto.getCompanyName());
        if (existingCompany.isPresent()) {
            throw new IllegalArgumentException("Company details already exist.");
        }

        if (companyDto.getCompanyName() != null) {
            company.setCompanyName(companyDto.getCompanyName());
        }
        MasterCompany updatedCompany = masterCompanyRepository.save(company);
        return modelMapper.map(updatedCompany, MasterCompanyDto.class);
    }

    @Override
    public void deleteCompany(Long  contactId) {
        MasterCompany company = masterCompanyRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Company  not found with id: " + contactId));
        masterCompanyRepository.delete(company);
    }

    @Override
    public MasterCompanyDto getCompanyById(Long companyId) {
        MasterCompany company = masterCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company  not found with id: " + companyId));
        return modelMapper.map(company, MasterCompanyDto.class);
    }

    @Override
    public List<MasterCompanyDto> getAllCompanies() {
        return masterCompanyRepository.findAll().stream()
                .map(company -> modelMapper.map(company, MasterCompanyDto.class))
                .collect(Collectors.toList());
    }

}
