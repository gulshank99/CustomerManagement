package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.services.ContactDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {
    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactDetailsDto createContact(ContactDetailsDto contactDetailsDto) {
        ContactDetails contactDetails = modelMapper.map(contactDetailsDto, ContactDetails.class);
        ContactDetails savedContact = contactDetailsRepository.save(contactDetails);
        return modelMapper.map(savedContact, ContactDetailsDto.class);
    }

    @Override
    public ContactDetailsDto updateContact(Long contactId, ContactDetailsDto contactDetailsDto) {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        // Update fields
        contactDetails.setEmailId(contactDetailsDto.getEmailId());
        contactDetails.setContactName(contactDetailsDto.getContactName());
        contactDetails.setPrimaryNumber(contactDetailsDto.getPrimaryNumber());
        contactDetails.setDesignation(contactDetailsDto.getDesignation());
        contactDetails.setSecondaryNumber(contactDetailsDto.getSecondaryNumber());
        contactDetails.setOtherSkills(contactDetailsDto.getOtherSkills());
        contactDetails.setCompanyName(contactDetailsDto.getCompanyName());
        contactDetails.setResume(contactDetailsDto.getResume());
        contactDetails.setImage(contactDetailsDto.getImage());
        contactDetails.setIsActive(contactDetailsDto.getIsActive());
        contactDetails.setCurrentSalary(contactDetailsDto.getCurrentSalary());
        contactDetails.setHiringType(contactDetailsDto.getHiringType());
        contactDetails.setLinkdinId(contactDetailsDto.getLinkdinId());
        contactDetails.setClientCompanyName(contactDetailsDto.getClientCompanyName());
        contactDetails.setDateTimeInserted(contactDetailsDto.getDateTimeInserted());

        // Handle updating skills, preferredLocations, and interviewsDetails

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

//    @Override
//    public List<ContactDetailsDto> searchContactsByKeyword(String query) {
//        List<ContactDetails> contacts = contactDetailsRepository.searchByKeyword(query);
//        return contacts.stream()
//                .map(contact -> modelMapper.map(contact, ContactDetailsDto.class))
//                .collect(Collectors.toList());
//    }

}
