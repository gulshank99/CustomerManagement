package com.sts.first.CustomerManagement.services.Impl;


import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.ClientJob;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import com.sts.first.CustomerManagement.entities.MasterClient;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ClientJobRepository;
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
    private ClientJobRepository clientJobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactInterviewsDto createContactInterview(ContactInterviewsDto contactInterviewsDto) {
        validateContactIdAndClientId(contactInterviewsDto);

        Long maxId = contactInterviewsRepository.findMaxId();
        Long newId = maxId + 1;
        contactInterviewsDto.setInterviewId(newId);

        Long contactId = contactInterviewsDto.getContactDetails().getContactId();
//        Long clientId = contactInterviewsDto.getClient().getClientId();
        Long jobId = contactInterviewsDto.getClientJob().getJobId();

        // Check if a combination of contactId and clientId already exists
//        Optional<ContactInterviews> existingContactInterview =
//                contactInterviewsRepository.findByContactDetailsContactIdAndClientClientId(contactId, clientId);
//
//        if (existingContactInterview.isPresent()) {
//            throw new IllegalArgumentException("The combination of contact ID and client ID already exists.");
//        }


        List<ContactInterviews> existingContactInterview  = contactInterviewsRepository.findByContactDetailsContactIdAndClientJobJobId(contactId, jobId);

        if (!existingContactInterview.isEmpty()) {
            throw new IllegalArgumentException("The combination of contact ID and jobId ID already exists.");
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
//        Long clientId = contactInterviewsDto.getClient().getClientId();
        Long jobId = contactInterviewsDto.getClientJob().getJobId();

        // Check if a combination of contactId and clientId already exists
//        Optional<ContactInterviews> existingContactInterview =
//                contactInterviewsRepository.findByContactDetailsContactIdAndClientClientId(contactId, clientId);
//
//        if (existingContactInterview.isPresent()) {
//            throw new IllegalArgumentException("The combination of contact ID and client ID already exists.");
//        }

        List<ContactInterviews> existingContactInterview  = contactInterviewsRepository.findByContactDetailsContactIdAndClientJobJobId(contactId, jobId);

        if (!existingContactInterview.isEmpty()) {
            throw new IllegalArgumentException("The combination of contact ID and jobId ID already exists.");
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
//        if (contactInterviewsDto.getClient() != null) {
//            MasterClient client = masterClientRepository.findById(contactInterviewsDto.getClient().getClientId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + contactInterviewsDto.getClient().getClientId()));
//            contactInterviews.setClient(client);
//        }

        if (contactInterviewsDto.getClientJob().getJobId()!= null) {
            ClientJob clientJob = clientJobRepository.findById(contactInterviewsDto.getClientJob().getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + contactInterviewsDto.getClientJob().getJobId()));
            contactInterviews.setClientJob(clientJob);
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



    @Override
    public List<ContactInterviewsDto> getAllContactsByJobId(Long jobId) {
        // Validate if job exists
        ClientJob clientJob = clientJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));

        // Find all interviews related to the jobId
        List<ContactInterviews> interviews = contactInterviewsRepository.findByClientJobJobId(jobId);

        List<ContactInterviewsDto> contactInterviewsDtos = interviews.stream()
                .map(interview -> modelMapper.map(interview, ContactInterviewsDto.class))
                .collect(Collectors.toList());

        if(contactInterviewsDtos.isEmpty()){
            throw new ResourceNotFoundException("No interviews found for job ID: " + jobId);
        }

        // Convert to DTOs and return
        return contactInterviewsDtos;
    }



    @Override
    public List<ContactInterviewsDto> getAllInterviewsByContactId(Long contactId) {
        // Validate if contact exists
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        // Find all interviews related to the contactId
        List<ContactInterviews> interviews = contactInterviewsRepository.findByContactDetailsContactId(contactId);

        List<ContactInterviewsDto> contactInterviewsDtos = interviews.stream()
                .map(interview -> modelMapper.map(interview, ContactInterviewsDto.class))
                .collect(Collectors.toList());

        if (contactInterviewsDtos.isEmpty()){
            throw new ResourceNotFoundException("No interviews found for contactId" + contactId );
        }
        // Convert to DTOs and return
        return contactInterviewsDtos;
    }


    @Override
    public List<ContactInterviewsDto> getAllInterviewsByContactIdAndClientId(Long contactId, Long clientId) {
        // Validate if contact exists
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        // Fetch all interviews for the given contactId and clientId
        List<ContactInterviews> interviews = contactInterviewsRepository
                .findByContactDetailsContactIdAndClientJobClientClientId(contactId, clientId);

        List<ContactInterviewsDto> contactInterviewsDtos = interviews.stream()
                .map(interview -> modelMapper.map(interview, ContactInterviewsDto.class))
                .collect(Collectors.toList());

        if (contactInterviewsDtos.isEmpty()){
            throw new ResourceNotFoundException("No interviews found with both ContactID and ClientID" );
        }
        // Convert to DTOs
        return contactInterviewsDtos;
    }




    private void validateContactIdAndClientId(ContactInterviewsDto contactInterviewsDto) {
        ContactDetailsDto contactDetails = contactInterviewsDto.getContactDetails();
//        MasterClientDto client = contactInterviewsDto.getClient();
        ClientJob clientJob = contactInterviewsDto.getClientJob();

        if (contactDetails == null || contactDetails.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID must be present in the Contact!");
        }

//        if (client == null || client.getClientId() == null) {
//            throw new IllegalArgumentException("Client ID must be present in the Domain!");
//        }


        if (clientJob == null || clientJob.getJobId() == null) {
            throw new IllegalArgumentException("Client Job ID must be present in the Client Job!");
        }


//        if (contactInterviewsDto.getClient().getClientId() != null) {
//            masterClientRepository.findById(contactInterviewsDto.getClient().getClientId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + contactInterviewsDto.getClient().getClientId()));
//
//        }

        if (contactInterviewsDto.getContactDetails().getContactId() != null) {
            contactDetailsRepository.findById(contactInterviewsDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactInterviewsDto.getContactDetails().getContactId()));

        }


        if (contactInterviewsDto.getClientJob() != null && contactInterviewsDto.getClientJob().getJobId() != null) {
            clientJobRepository.findById(contactInterviewsDto.getClientJob().getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client Job not found with id: " + contactInterviewsDto.getClientJob().getJobId()));
        }

    }
}