package com.sts.first.CustomerManagement.services.Impl;


import com.sts.first.CustomerManagement.dtos.MasterDomainDto;
import com.sts.first.CustomerManagement.entities.MasterDomain;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.MasterDomainRepository;
import com.sts.first.CustomerManagement.services.MasterDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterDomainServiceImpl implements MasterDomainService {

    @Autowired
    private MasterDomainRepository masterDomainRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MasterDomainDto createDomain(MasterDomainDto domainDto) {

        Long maxId = masterDomainRepository.findMaxId();
        Long newId = maxId + 1;
        domainDto.setDomainId(newId);

        Optional<MasterDomain> existingDomain = masterDomainRepository.findByDomainDetails(domainDto.getDomainDetails());
        if (existingDomain.isPresent()) {
            throw new IllegalArgumentException("Domain details already exist.");
        }
        MasterDomain domain = modelMapper.map(domainDto, MasterDomain.class);
        MasterDomain savedDomain = masterDomainRepository.save(domain);
        return modelMapper.map(savedDomain, MasterDomainDto.class);
    }

    @Override
    public MasterDomainDto updateDomain(Long domainId, MasterDomainDto domainDto) {
        MasterDomain domain = masterDomainRepository.findById(domainId)
                .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + domainId));

        Optional<MasterDomain> existingDomain = masterDomainRepository.findByDomainDetails(domainDto.getDomainDetails());
        if (existingDomain.isPresent()) {
            throw new IllegalArgumentException("Domain details already exist.");
        }

        if (domainDto.getDomainDetails() != null) {
            domain.setDomainDetails(domainDto.getDomainDetails());
        }
        MasterDomain updatedDomain = masterDomainRepository.save(domain);
        return modelMapper.map(updatedDomain, MasterDomainDto.class);
    }

    @Override
    public void deleteDomain(Long domainId) {
        MasterDomain domain = masterDomainRepository.findById(domainId)
                .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + domainId));
        masterDomainRepository.delete(domain);
    }

    @Override
    public MasterDomainDto getDomainById(Long domainId) {
        MasterDomain domain = masterDomainRepository.findById(domainId)
                .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + domainId));
        return modelMapper.map(domain, MasterDomainDto.class);
    }

    @Override
    public List<MasterDomainDto> getAllDomains() {
        return masterDomainRepository.findAll().stream()
                .map(domain -> modelMapper.map(domain, MasterDomainDto.class))
                .collect(Collectors.toList());
    }
}
