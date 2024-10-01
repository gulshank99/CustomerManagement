package com.sts.first.CustomerManagement.services.Impl;


import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import com.sts.first.CustomerManagement.entities.MasterClient;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.repositories.ContactInterviewRepository;
import com.sts.first.CustomerManagement.repositories.MasterClientRepository;
import com.sts.first.CustomerManagement.services.ContactInterviewService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactInterviewsServiceImpl implements ContactInterviewService {

    @Autowired
    private ContactInterviewRepository contactInterviewsRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private MasterClientRepository masterClientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactInterviewsDto createContactInterview(ContactInterviewsDto contactInterviewsDto) {
        validateContactIdAndClientId(contactInterviewsDto);

        Long maxId = contactInterviewsRepository.findMaxId();
        Long newId = maxId + 1;
        contactInterviewsDto.setInterviewId(newId);

        Long contactId = contactInterviewsDto.getContactDetails().getContactId();
        Long clientId = contactInterviewsDto.getClient().getClientId();

        // Check if a combination of contactId and clientId already exists
        Optional<ContactInterviews> existingContactInterview =
                contactInterviewsRepository.findByContactDetailsContactIdAndClientClientId(contactId, clientId);

        if (existingContactInterview.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and client ID already exists.");
        }

        ContactInterviews contactInterviews = modelMapper.map(contactInterviewsDto, ContactInterviews.class);
        ContactInterviews savedContactInterviews = contactInterviewsRepository.save(contactInterviews);
        return modelMapper.map(savedContactInterviews, ContactInterviewsDto.class);
    }

    @Override
    public ContactInterviewsDto updateContactInterview(Long id, ContactInterviewsDto contactInterviewsDto) {
        ContactInterviews contactInterviews = contactInterviewsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + id));

//        validateContactIdAndClientId(contactInterviewsDto);

        // Update all fields

        Long contactId = contactInterviewsDto.getContactDetails().getContactId();
        Long clientId = contactInterviewsDto.getClient().getClientId();

        // Check if a combination of contactId and clientId already exists
        Optional<ContactInterviews> existingContactInterview =
                contactInterviewsRepository.findByContactDetailsContactIdAndClientClientId(contactId, clientId);

        if (existingContactInterview.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and client ID already exists.");
        }

        if (contactInterviewsDto.getInterviewDate() != null) {
            contactInterviews.setInterviewDate(contactInterviewsDto.getInterviewDate());
        }
        if (contactInterviewsDto.getInterviewStatus() != null) {
            contactInterviews.setInterviewStatus(contactInterviewsDto.getInterviewStatus());
        }

        // Update the associated ContactDetails if provided
        if (contactInterviewsDto.getContactDetails() != null) {
            ContactDetails contactDetails = contactDetailsRepository.findById(contactInterviewsDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactInterviewsDto.getContactDetails().getContactId()));
            contactInterviews.setContactDetails(contactDetails);
        }

        // Update the associated MasterClient if provided
        if (contactInterviewsDto.getClient() != null) {
            MasterClient client = masterClientRepository.findById(contactInterviewsDto.getClient().getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + contactInterviewsDto.getClient().getClientId()));
            contactInterviews.setClient(client);
        }

        // Save the updated entity
        ContactInterviews updatedContactInterviews = contactInterviewsRepository.save(contactInterviews);
        return modelMapper.map(updatedContactInterviews, ContactInterviewsDto.class);
    }


    @Override
    public void deleteContactInterview(Long id) {
        ContactInterviews contactInterviews = contactInterviewsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + id));
        contactInterviewsRepository.delete(contactInterviews);
    }

    @Override
    public ContactInterviewsDto getContactInterviewById(Long id) {
        ContactInterviews contactInterviews = contactInterviewsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + id));
        return modelMapper.map(contactInterviews, ContactInterviewsDto.class);
    }

    @Override
    public List<ContactInterviewsDto> getAllContactInterviews() {
        return contactInterviewsRepository.findAll().stream()
                .map(interview -> modelMapper.map(interview, ContactInterviewsDto.class))
                .collect(Collectors.toList());
    }


    private void validateContactIdAndClientId(ContactInterviewsDto contactInterviewsDto) {
        ContactDetailsDto contactDetails = contactInterviewsDto.getContactDetails();
        MasterClientDto client = contactInterviewsDto.getClient();

        if (contactDetails == null || contactDetails.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID must be present in the Contact!");
        }

        if (client == null || client.getClientId() == null) {
            throw new IllegalArgumentException("Client ID must be present in the Domain!");
        }

        if (contactInterviewsDto.getClient().getClientId() != null) {
            masterClientRepository.findById(contactInterviewsDto.getClient().getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + contactInterviewsDto.getClient().getClientId()));

        }

        if (contactInterviewsDto.getContactDetails() != null && contactInterviewsDto.getContactDetails().getContactId() != null) {
            contactDetailsRepository.findById(contactInterviewsDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactInterviewsDto.getContactDetails().getContactId()));

        }

    }
}