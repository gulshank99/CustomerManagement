package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.dtos.ContactDomainsDto;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactDomainsRepository;
import com.sts.first.CustomerManagement.services.ContactDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactDomainsServiceImpl implements ContactDomainService {
    @Autowired
    private ContactDomainsRepository contactDomainsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactDomainsDto createContactDomain(ContactDomainsDto contactDomainsDto) {
        ContactDomains contactDomain = modelMapper.map(contactDomainsDto, ContactDomains.class);
        ContactDomains savedContactDomain = contactDomainsRepository.save(contactDomain);
        return modelMapper.map(savedContactDomain, ContactDomainsDto.class);
    }

    @Override
    public ContactDomainsDto updateContactDomain(Long id, ContactDomainsDto contactDomainsDto) {
        ContactDomains contactDomain = contactDomainsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact domain not found with id: " + id));

        contactDomain.setIsPrimaryDomain(contactDomainsDto.getIsPrimaryDomain());
        contactDomain.setIsSecondaryDomain(contactDomainsDto.getIsSecondaryDomain());
        ContactDomains updatedContactDomain = contactDomainsRepository.save(contactDomain);
        return modelMapper.map(updatedContactDomain, ContactDomainsDto.class);
    }

    @Override
    public void deleteContactDomain(Long id) {
        ContactDomains contactDomain = contactDomainsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact domain not found with id: " + id));
        contactDomainsRepository.delete(contactDomain);
    }

    @Override
    public ContactDomainsDto getContactDomainById(Long id) {
        ContactDomains contactDomain = contactDomainsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact domain not found with id: " + id));
        return modelMapper.map(contactDomain, ContactDomainsDto.class);
    }

    @Override
    public List<ContactDomainsDto> getAllContactDomains() {
        return contactDomainsRepository.findAll().stream()
                .map(domains -> modelMapper.map(domains, ContactDomainsDto.class))
                .collect(Collectors.toList());
    }


}
