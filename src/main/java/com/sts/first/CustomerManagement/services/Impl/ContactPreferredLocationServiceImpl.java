package com.sts.first.CustomerManagement.services.Impl;
import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactPreferredLocation;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import com.sts.first.CustomerManagement.exceptions.DuplicateResourceException;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.repositories.ContactPreferredLocationRepository;
import com.sts.first.CustomerManagement.repositories.MasterLocationRepository;
import com.sts.first.CustomerManagement.services.ContactPreferredLocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactPreferredLocationServiceImpl implements ContactPreferredLocationService {

    @Autowired
    private ContactPreferredLocationRepository contactPreferredLocationRepository;
    @Autowired
    private MasterLocationRepository masterLocationRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactPreferredLocationDto createPreferredLocation(ContactPreferredLocationDto preferredLocationDto) {
        validateContactIdAndLocationId(preferredLocationDto);

        Long maxId = contactPreferredLocationRepository.findMaxId();
        Long newId = maxId + 1;
        preferredLocationDto.setPrefLocationId(newId);

        Long contactId = preferredLocationDto.getContactDetails().getContactId();
        Long locationId = preferredLocationDto.getLocation().getLocationId();

        // Check if a combination of contactId and locationId already exists
        Optional<ContactPreferredLocation> existingContactPreferredLocation =
                contactPreferredLocationRepository.findByContactDetailsContactIdAndLocationLocationId(contactId, locationId);

        if (existingContactPreferredLocation.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and location ID already exists.");
        }

        ContactPreferredLocation preferredLocation = modelMapper.map(preferredLocationDto, ContactPreferredLocation.class);
        ContactPreferredLocation savedPreferredLocation = contactPreferredLocationRepository.save(preferredLocation);
        return modelMapper.map(savedPreferredLocation, ContactPreferredLocationDto.class);
    }

    @Override
    public ContactPreferredLocationDto updatePreferredLocation(Long id, ContactPreferredLocationDto preferredLocationDto) {
//        validateContactIdAndLocationId(preferredLocationDto);

        ContactPreferredLocation preferredLocation = contactPreferredLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preferred location not found with id: " + id));

        // Fetch and set related entities
        Long contactId = preferredLocationDto.getContactDetails().getContactId();
        Long locationId = preferredLocationDto.getLocation().getLocationId();

        // Check if a combination of contactId and locationId already exists
        Optional<ContactPreferredLocation> existingContactPreferredLocation =
                contactPreferredLocationRepository.findByContactDetailsContactIdAndLocationLocationId(contactId, locationId);

        if (existingContactPreferredLocation.isPresent()) {
            throw new IllegalArgumentException("The combination of contact ID and location ID already exists.");
        }

        if (preferredLocationDto.getLocation() != null && preferredLocationDto.getLocation().getLocationId() != null) {
            MasterLocation masterLocation = masterLocationRepository.findById(preferredLocationDto.getLocation().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + preferredLocationDto.getLocation().getLocationId()));
            preferredLocation.setLocation(masterLocation);
        }

        if (preferredLocationDto.getContactDetails() != null && preferredLocationDto.getContactDetails().getContactId() != null) {
            ContactDetails contactDetails = contactDetailsRepository.findById(preferredLocationDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + preferredLocationDto.getContactDetails().getContactId()));
            preferredLocation.setContactDetails(contactDetails);
        }

        ContactPreferredLocation updatedPreferredLocation = contactPreferredLocationRepository.save(preferredLocation);
        return modelMapper.map(updatedPreferredLocation, ContactPreferredLocationDto.class);
    }

    @Override
    public void deletePreferredLocation(Long id) {
        ContactPreferredLocation preferredLocation = contactPreferredLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preferred location not found with id: " + id));
        contactPreferredLocationRepository.delete(preferredLocation);
    }

    @Override
    public ContactPreferredLocationDto getPreferredLocationById(Long id) {
        ContactPreferredLocation preferredLocation = contactPreferredLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preferred location not found with id: " + id));
        return modelMapper.map(preferredLocation, ContactPreferredLocationDto.class);
    }

    @Override
    public List<ContactPreferredLocationDto> getAllPreferredLocations() {
        return contactPreferredLocationRepository.findAll().stream()
                .map(preferredLocation -> modelMapper.map(preferredLocation, ContactPreferredLocationDto.class))
                .collect(Collectors.toList());
    }

    private void validateContactIdAndLocationId(ContactPreferredLocationDto contactPreferredLocationDto) {
        ContactDetailsDto contactDetails = contactPreferredLocationDto.getContactDetails();
        MasterLocationDto location = contactPreferredLocationDto.getLocation();

        if (contactDetails == null || contactDetails.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID must be present in the Contact!");
        }

        if (location == null || location.getLocationId() == null) {
            throw new IllegalArgumentException("Location ID must be present in the Location!");
        }

        if (contactPreferredLocationDto.getLocation().getLocationId() != null) {
            masterLocationRepository.findById(contactPreferredLocationDto.getLocation().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " +  contactPreferredLocationDto.getLocation().getLocationId()));
        }

        if (contactPreferredLocationDto.getContactDetails() != null && contactPreferredLocationDto.getContactDetails().getContactId() != null) {
            contactDetailsRepository.findById(contactPreferredLocationDto.getContactDetails().getContactId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactPreferredLocationDto.getContactDetails().getContactId()));

        }

    }
}
