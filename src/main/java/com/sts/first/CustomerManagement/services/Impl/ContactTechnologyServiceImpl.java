package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactTechnology;
import com.sts.first.CustomerManagement.entities.MasterTechnology;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.repositories.ContactTechnologyRepository;
import com.sts.first.CustomerManagement.repositories.MasterTechnologyRepository;
import com.sts.first.CustomerManagement.services.ContactTechnologyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ContactTechnologyServiceImpl implements ContactTechnologyService {
    @Autowired
    private ContactTechnologyRepository contactsTechnologyRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private MasterTechnologyRepository masterTechnologyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactTechnologyDto createContactTechnology(ContactTechnologyDto contactsTechnologyDto) {
        validateContactIdAndTechId(contactsTechnologyDto);

        Long maxId = contactsTechnologyRepository.findMaxId();
        Long newId = maxId + 1;
        contactsTechnologyDto.setContactTechId(newId);



        Long contactId = contactsTechnologyDto.getContactDetails().getContactId();
        Long techId = contactsTechnologyDto.getTechnology().getTechId();

        // Check if a combination of contactId and techId already exists
        Optional<ContactTechnology> existingContactTechnology =
                contactsTechnologyRepository.findByContactDetailsContactIdAndTechnologyTechId(contactId, techId);

        if (existingContactTechnology.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and technology ID already exists.");
        }
        ContactTechnology contactsTechnology = modelMapper.map(contactsTechnologyDto, ContactTechnology.class);
        ContactTechnology savedContactsTechnology = contactsTechnologyRepository.save(contactsTechnology);
        return modelMapper.map(savedContactsTechnology, ContactTechnologyDto.class);
    }

    @Override
    public ContactTechnologyDto updateContactTechnology(Long id, ContactTechnologyDto contactsTechnologyDto) {
        ContactTechnology contactsTechnology = contactsTechnologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacts technology not found with id: " + id));
//        validateContactIdAndTechId(contactsTechnologyDto);

        Long contactId = contactsTechnologyDto.getContactDetails().getContactId();
        Long techId = contactsTechnologyDto.getTechnology().getTechId();

        // Check if a combination of contactId and techId already exists
        Optional<ContactTechnology> existingContactTechnology =
                contactsTechnologyRepository.findByContactDetailsContactIdAndTechnologyTechId(contactId, techId);

        if (existingContactTechnology.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and technology ID already exists.");
        }


        if (contactsTechnologyDto.getExperience() != null) {
            contactsTechnology.setExperience(contactsTechnologyDto.getExperience());
        }
        if (contactsTechnologyDto.getExpertiseLevel() != null) {
            contactsTechnology.setExpertiseLevel(contactsTechnologyDto.getExpertiseLevel());
        }
        if (contactsTechnologyDto.getIsPrimary() != null) {
            contactsTechnology.setIsPrimary(contactsTechnologyDto.getIsPrimary());
        }
        if (contactsTechnologyDto.getIsSecondary() != null) {
            contactsTechnology.setIsSecondary(contactsTechnologyDto.getIsSecondary());
        }

        if (contactsTechnologyDto.getContactDetails().getContactId() != null) {
            ContactDetails contactDetails = contactDetailsRepository.findById(contactsTechnologyDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactsTechnologyDto.getContactDetails().getContactId()));

            contactsTechnology.setContactDetails(contactDetails);
        }

        if (contactsTechnologyDto.getTechnology().getTechId() != null) {
            MasterTechnology masterTechnology = masterTechnologyRepository.findById(contactsTechnologyDto.getTechnology().getTechId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technology not found with id: " + contactsTechnologyDto.getTechnology().getTechId()));

            contactsTechnology.setTechnology(masterTechnology);
        }

        ContactTechnology updatedContactsTechnology = contactsTechnologyRepository.save(contactsTechnology);
        return modelMapper.map(updatedContactsTechnology, ContactTechnologyDto.class);
    }

    @Override
    public void deleteContactTechnology(Long id) {
        ContactTechnology contactsTechnology = contactsTechnologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacts technology not found with id: " + id));
        contactsTechnologyRepository.delete(contactsTechnology);
    }

    @Override
    public ContactTechnologyDto getContactTechnologyById(Long id) {
        ContactTechnology contactsTechnology = contactsTechnologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacts technology not found with id: " + id));
        return modelMapper.map(contactsTechnology, ContactTechnologyDto.class);
    }

    @Override
    public List<ContactTechnologyDto> getAllContactTechnologies() {
        return contactsTechnologyRepository.findAll().stream()
                .map(contactsTechnology -> modelMapper.map(contactsTechnology, ContactTechnologyDto.class))
                .collect(Collectors.toList());
    }


    private void validateContactIdAndTechId(ContactTechnologyDto contactTechnologyDto) {
        ContactDetailsDto contactDetails = contactTechnologyDto.getContactDetails();
        MasterTechnologyDto technology = contactTechnologyDto.getTechnology();

        if (contactDetails == null || contactDetails.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID must be present in the Contact!");
        }

        if (technology == null || technology.getTechId() == null) {
            throw new IllegalArgumentException("Tech ID must be present in the Location!");
        }

        if (contactTechnologyDto.getTechnology().getTechId() != null) {
            masterTechnologyRepository.findById(contactTechnologyDto.getTechnology().getTechId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technology not found with id: " +  contactTechnologyDto.getTechnology().getTechId()));
        }

        if (contactTechnologyDto.getContactDetails() != null && contactTechnologyDto.getContactDetails().getContactId() != null) {
            contactDetailsRepository.findById(contactTechnologyDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactTechnologyDto.getContactDetails().getContactId()));

        }

    }
}
