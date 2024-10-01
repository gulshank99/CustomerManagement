package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactDomains;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import com.sts.first.CustomerManagement.entities.MasterDomain;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.repositories.ContactDomainsRepository;
import com.sts.first.CustomerManagement.repositories.MasterDomainRepository;
import com.sts.first.CustomerManagement.services.ContactDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactDomainsServiceImpl implements ContactDomainService {
    @Autowired
    private ContactDomainsRepository contactDomainsRepository;

    @Autowired
    private MasterDomainRepository masterDomainRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactDomainsDto createContactDomain(ContactDomainsDto contactDomainsDto) {
        validateContactIdAndDomainId(contactDomainsDto);

        Long maxId = contactDomainsRepository.findMaxId();
        Long newId = maxId + 1;
        contactDomainsDto.setContactDomainId(newId);


        Long contactId = contactDomainsDto.getContactDetails().getContactId();
        Long domainId = contactDomainsDto.getDomain().getDomainId();

        // Check if a combination of contactId and domainId already exists
        Optional<ContactDomains> existingContactDomain =
                contactDomainsRepository.findByContactDetailsContactIdAndDomainDomainId(contactId, domainId);

        if (existingContactDomain.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and domain ID already exists.");
        }

        ContactDomains contactDomain = modelMapper.map(contactDomainsDto, ContactDomains.class);
        ContactDomains savedContactDomain = contactDomainsRepository.save(contactDomain);
        return modelMapper.map(savedContactDomain, ContactDomainsDto.class);
    }

    @Override
    public ContactDomainsDto updateContactDomain(Long id, ContactDomainsDto contactDomainsDto) {
        ContactDomains contactDomain = contactDomainsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact domain not found with id: " + id));

//        validateContactIdAndDomainId(contactDomainsDto);
        Long contactId = contactDomainsDto.getContactDetails().getContactId();
        Long domainId = contactDomainsDto.getDomain().getDomainId();

        Optional<ContactDomains> existingContactDomain =
                contactDomainsRepository.findByContactDetailsContactIdAndDomainDomainId(contactId, domainId);

        if (existingContactDomain.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and domain ID already exists.");
        }
        if (contactDomainsDto.getIsPrimaryDomain() != null) {
            contactDomain.setIsPrimaryDomain(contactDomainsDto.getIsPrimaryDomain());
        }
        if (contactDomainsDto.getIsSecondaryDomain() != null) {
            contactDomain.setIsSecondaryDomain(contactDomainsDto.getIsSecondaryDomain());
        }
//        contactDomain.setIsPrimaryDomain(contactDomainsDto.getIsPrimaryDomain());
//        contactDomain.setIsSecondaryDomain(contactDomainsDto.getIsSecondaryDomain());


        if (contactDomainsDto.getDomain() != null && contactDomainsDto.getDomain().getDomainId() != null) {
            MasterDomain masterDomain = masterDomainRepository.findById(contactDomainsDto.getDomain().getDomainId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + contactDomainsDto.getDomain().getDomainId()));
            contactDomain.setDomain(masterDomain);
        }

        if (contactDomainsDto.getContactDetails() != null && contactDomainsDto.getContactDetails().getContactId() != null) {
            ContactDetails contactDetails = contactDetailsRepository.findById(contactDomainsDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactDomainsDto.getContactDetails().getContactId()));
            contactDomain.setContactDetails(contactDetails);
        }

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

    private void validateContactIdAndDomainId(ContactDomainsDto contactDomainsDto) {
        ContactDetailsDto contactDetails = contactDomainsDto.getContactDetails();
        MasterDomainDto domain = contactDomainsDto.getDomain();

        if (contactDetails == null || contactDetails.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID must be present in the Contact!");
        }

        if (domain == null || domain.getDomainId() == null) {
            throw new IllegalArgumentException("Domain ID must be present in the Domain!");
        }

        if (contactDomainsDto.getDomain().getDomainId() != null) {
             masterDomainRepository.findById(contactDomainsDto.getDomain().getDomainId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + contactDomainsDto.getDomain().getDomainId()));

        }

        if (contactDomainsDto.getContactDetails() != null && contactDomainsDto.getContactDetails().getContactId() != null) {
          contactDetailsRepository.findById(contactDomainsDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + contactDomainsDto.getContactDetails().getContactId()));

        }

    }


}
