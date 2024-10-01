package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.*;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactCompanyRepository;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.repositories.MasterCompanyRepository;
import com.sts.first.CustomerManagement.services.ContactCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ContactCompanyServiceImpl implements ContactCompanyService {


    @Autowired
    private ContactCompanyRepository contactCompanyRepository;

    @Autowired
    private MasterCompanyRepository masterCompanyRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactCompanyDto createContactCompany(ContactCompanyDto contactCompanyDto) {
        validateContactIdAndContactCompanyId(contactCompanyDto);

        Long maxId = contactCompanyRepository.findMaxId();
        Long newId = maxId + 1;
        contactCompanyDto.setContactCompanyId(newId);


        Long contactId = contactCompanyDto.getContactDetails().getContactId();
        Long companyId = contactCompanyDto.getCompany().getCompanyId();


        // Check if a combination of contactId and domainId already exists
        Optional<ContactCompany> existingContactCompany =
                contactCompanyRepository.findByContactDetailsContactIdAndCompanyCompanyId(contactId, companyId);

        if (existingContactCompany.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and company ID already exists.");
        }

        ContactCompany  contactCompany = modelMapper.map(contactCompanyDto, ContactCompany.class);
        ContactCompany savedcontactCompany = contactCompanyRepository.save(contactCompany);
        return modelMapper.map(savedcontactCompany, ContactCompanyDto.class);
    }

    @Override
    public ContactCompanyDto updateContactCompany(Long contactCompanyId, ContactCompanyDto  contactCompanyDto) {
        ContactCompany contactCompany = contactCompanyRepository.findById(contactCompanyId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact company not found with id: " + contactCompanyId));

//        validateContactIdAndDomainId(contactDomainsDto);
        Long contactId = contactCompanyDto.getContactDetails().getContactId();
        Long companyId = contactCompanyDto.getCompany().getCompanyId();

        Optional<ContactCompany> existingContactCompany  =
                contactCompanyRepository.findByContactDetailsContactIdAndCompanyCompanyId(contactId, companyId);

        if (existingContactCompany.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and company ID already exists.");
        }


        if (contactCompanyDto.getCompany() != null && contactCompanyDto.getCompany().getCompanyId() != null) {
            MasterCompany masterCompany = masterCompanyRepository.findById(contactCompanyDto.getCompany().getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + contactCompanyDto.getContactDetails().getContactId()));
            contactCompany.setCompany(masterCompany);
        }

        if (contactCompanyDto.getContactDetails() != null && contactCompanyDto.getContactDetails().getContactId() != null) {
            ContactDetails contactDetails = contactDetailsRepository.findById(contactCompanyDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactCompanyDto.getContactDetails().getContactId()));
            contactCompany.setContactDetails(contactDetails);
        }

        ContactCompany savedContactCompany = contactCompanyRepository.save(contactCompany);
        return modelMapper.map(savedContactCompany, ContactCompanyDto.class);

    }

    @Override
    public void deleteContactDomain(Long contactCompanyId) {
        ContactCompany  contactCompany =  contactCompanyRepository.findById(contactCompanyId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Company not found with id: " + contactCompanyId));
        contactCompanyRepository.delete(contactCompany);
    }

    @Override
    public ContactCompanyDto getContactCompanyById(Long contactCompanyId) {
        ContactCompany  contactCompany = contactCompanyRepository.findById(contactCompanyId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Company not found with id: " + contactCompanyId));
        return modelMapper.map(contactCompany, ContactCompanyDto.class);

    }

    @Override
    public List<ContactCompanyDto> getAllContactCompanies() {
        return contactCompanyRepository.findAll().stream()
                .map( contactCompany -> modelMapper.map(contactCompany , ContactCompanyDto.class))
                .collect(Collectors.toList());
    }

    private void validateContactIdAndContactCompanyId(ContactCompanyDto  contactCompanyDto )  {
        ContactDetailsDto contactDetails = contactCompanyDto.getContactDetails();
        MasterCompanyDto company = contactCompanyDto.getCompany();

        if (contactDetails == null || contactDetails.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID must be present in the Contact!");
        }

        if (company == null || company.getCompanyId() == null) {
            throw new IllegalArgumentException("Company ID must be present in the Company !");
        }

        if (contactCompanyDto.getCompany().getCompanyId() != null) {
            masterCompanyRepository.findById(contactCompanyDto.getCompany().getCompanyId() )
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + contactCompanyDto.getCompany().getCompanyId()));

        }

        if (contactCompanyDto.getContactDetails() != null && contactCompanyDto.getContactDetails().getContactId() != null) {
            contactDetailsRepository.findById(contactCompanyDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + contactCompanyDto.getContactDetails().getContactId()));

        }

    }

    }
