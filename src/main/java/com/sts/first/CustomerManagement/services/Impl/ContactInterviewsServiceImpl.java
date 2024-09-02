package com.sts.first.CustomerManagement.services.Impl;


import com.sts.first.CustomerManagement.dtos.ContactInterviewsDto;
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
        ContactInterviews contactInterviews = modelMapper.map(contactInterviewsDto, ContactInterviews.class);
        ContactInterviews savedContactInterviews = contactInterviewsRepository.save(contactInterviews);
        return modelMapper.map(savedContactInterviews, ContactInterviewsDto.class);
    }

    @Override
    public ContactInterviewsDto updateContactInterview(Long id, ContactInterviewsDto contactInterviewsDto) {
        ContactInterviews contactInterviews = contactInterviewsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + id));

        // Update all fields
        contactInterviews.setInterviewDate(contactInterviewsDto.getInterviewDate());
        contactInterviews.setInterviewStatus(contactInterviewsDto.getInterviewStatus());

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
}