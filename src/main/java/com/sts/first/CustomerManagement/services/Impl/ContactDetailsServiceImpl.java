package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.entities.*;
import com.sts.first.CustomerManagement.exceptions.BadApiRequestException;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.*;
import com.sts.first.CustomerManagement.services.ContactDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {
    @Autowired
    private ContactDetailsRepository contactDetailsRepository;
    @Autowired
    private MasterDomainRepository masterDomainRepository;

    @Autowired
    private MasterTechnologyRepository masterTechnologyRepository;

    @Autowired
    private MasterLocationRepository masterLocationRepository;

    @Autowired
    private ContactInterviewRepository contactInterviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactDetailsDto createContact(ContactDetailsDto contactDetailsDto) {
        ContactDetails savedContact;
//        try {
            ContactDetails contactDetails = modelMapper.map(contactDetailsDto, ContactDetails.class);
            savedContact = contactDetailsRepository.save(contactDetails);
//        } catch (DataIntegrityViolationException ex) {
//            throw new BadApiRequestException("Duplicate entry found" );
//        }

        return modelMapper.map(savedContact, ContactDetailsDto.class);
    }

    @Override
    public ContactDetailsDto updateContact(Long contactId, ContactDetailsDto contactDetailsDto) {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        // Update fields
        // Update basic fields
        contactDetails.setContactName(contactDetailsDto.getContactName());
        contactDetails.setDob(contactDetailsDto.getDob());
        contactDetails.setPrimaryNumber(contactDetailsDto.getPrimaryNumber());
        contactDetails.setDesignation(contactDetailsDto.getDesignation());
        contactDetails.setSecondaryNumber(contactDetailsDto.getSecondaryNumber());
        contactDetails.setCompanyName(contactDetailsDto.getCompanyName());
        contactDetails.setResume(contactDetailsDto.getResume());
        contactDetails.setEmailId(contactDetailsDto.getEmailId());
        contactDetails.setTotalExperience(contactDetailsDto.getTotalExperience());
        contactDetails.setImage(contactDetailsDto.getImage());
        contactDetails.setIsActive(contactDetailsDto.getIsActive());
        contactDetails.setIsInterviewDone(contactDetailsDto.getIsInterviewDone());
        contactDetails.setCurrentSalary(contactDetailsDto.getCurrentSalary());
        contactDetails.setGender(contactDetailsDto.getGender());
        contactDetails.setHighestEducation(contactDetailsDto.getHighestEducation());
        contactDetails.setHiringType(contactDetailsDto.getHiringType());
        contactDetails.setTechRole(contactDetailsDto.getTechRole());
        contactDetails.setNoticePeriod(contactDetailsDto.getNoticePeriod());

        // Fetch and set related entities
//        if (contactDetailsDto.getInterview() != null && contactDetailsDto.getInterview().getInterviewId() != null) {
//            ContactInterviews interview = contactInterviewRepository.findById(contactDetailsDto.getInterview().getInterviewId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + contactDetailsDto.getInterview().getInterviewId()));
//            contactDetails.setInterview(interview);
//        }

        if (contactDetailsDto.getCurrentLocation() != null && contactDetailsDto.getCurrentLocation().getLocationId() != null) {
            MasterLocation currentLocation = masterLocationRepository.findById(contactDetailsDto.getCurrentLocation().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + contactDetailsDto.getCurrentLocation().getLocationId()));
            contactDetails.setCurrentLocation(currentLocation);
        }

        // Save the updated contact
        ContactDetails updatedContact = contactDetailsRepository.save(contactDetails);
        return modelMapper.map(updatedContact, ContactDetailsDto.class);
    }

    @Override
    public void deleteContact(Long contactId) {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        contactDetailsRepository.delete(contactDetails);
    }

    @Override
    public ContactDetailsDto getContactById(Long contactId) {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        return modelMapper.map(contactDetails, ContactDetailsDto.class);
    }

    @Override
    public List<ContactDetailsDto> getAllContacts() {
        return contactDetailsRepository.findAll().stream()
                .map(contact -> modelMapper.map(contact, ContactDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ContactDetailsDto> searchContactsByKeyword(String query, String filterType) {
        List<ContactDetails> contacts;
        try {
            switch (filterType.toLowerCase()) {
                case "name":
                    contacts = contactDetailsRepository.findByContactNameContainingIgnoreCase(query);
                    break;
                case "phone":
                    Long longQuery = Long.parseLong(query); // Parse query only for phone filter type
                    contacts = contactDetailsRepository.findByPrimaryNumberOrSecondaryNumber(longQuery, longQuery);
                    break;
                case "email":
                    contacts = contactDetailsRepository.findByEmailIdContainingIgnoreCase(query);
                    break;
                case "designation":
                    contacts = contactDetailsRepository.findByDesignationContainingIgnoreCase(query);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid filter type: " + filterType);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid phone number format. Please provide a valid number.", e);
        }

        return contacts.stream()
                .map(contact -> modelMapper.map(contact, ContactDetailsDto.class))
                .collect(Collectors.toList());
    }

}

