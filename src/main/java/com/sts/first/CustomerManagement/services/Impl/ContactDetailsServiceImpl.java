package com.sts.first.CustomerManagement.services.Impl;
import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.dtos.MasterLocationDto;
import com.sts.first.CustomerManagement.entities.*;
import com.sts.first.CustomerManagement.exceptions.DuplicateResourceException;
import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.*;
import com.sts.first.CustomerManagement.services.ContactDetailsService;
import com.sts.first.CustomerManagement.services.FileService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
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


    @Autowired
    private FileService fileService;

    private final String resumePath = "Assets/Resumes/";
    private final String imagePath = "Assets/Images/";

    Logger logger = LoggerFactory.getLogger(ContactDetailsServiceImpl.class);

    @Override
    public ContactDetailsDto createContact(ContactDetailsDto contactDetailsDto) {

        validateLocationId(contactDetailsDto);

        Long maxId = contactDetailsRepository.findMaxId();
        Long newId = maxId + 1;
        contactDetailsDto.setContactId(newId);

        if (contactDetailsRepository.findByPrimaryNumber(contactDetailsDto.getPrimaryNumber()).isPresent()) {
            throw new DuplicateResourceException("Primary number already exists. Please use a different primary number.");
        }

        // Check if a contact with the given secondary number already exists
        if (contactDetailsRepository.findBySecondaryNumber(contactDetailsDto.getSecondaryNumber()).isPresent()) {
            throw new DuplicateResourceException("Secondary number already exists. Please use a different secondary number.");
        }

        // Check if a contact with the given email already exists
        if (contactDetailsRepository.findByEmailId(contactDetailsDto.getEmailId()).isPresent()) {
            throw new DuplicateResourceException("Email ID already exists. Please use a different email.");
        }

//        ContactDetails savedContact;
        ContactDetails contactDetails = modelMapper.map(contactDetailsDto, ContactDetails.class);
        ContactDetails savedContact = contactDetailsRepository.save(contactDetails);

      logger.info("Contact created successfully with id: {}", savedContact.getContactId());
        return modelMapper.map(savedContact, ContactDetailsDto.class);
    }

    @Override
    public ContactDetailsDto updateContact(Long contactId, ContactDetailsDto contactDetailsDto) {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));


        // Update fields only if they are provided (not null)

        if (contactDetailsRepository.findByPrimaryNumber(contactDetailsDto.getPrimaryNumber()).isPresent()) {
            throw new DuplicateResourceException("Primary number already exists. Please use a different primary number.");
        }

        // Check if a contact with the given secondary number already exists
        if (contactDetailsRepository.findBySecondaryNumber(contactDetailsDto.getSecondaryNumber()).isPresent()) {
            throw new DuplicateResourceException("Secondary number already exists. Please use a different secondary number.");
        }

        // Check if a contact with the given email already exists
        if (contactDetailsRepository.findByEmailId(contactDetailsDto.getEmailId()).isPresent()) {
            throw new DuplicateResourceException("Email ID already exists. Please use a different email.");
        }


        if (contactDetailsDto.getFirstName() != null) {
            contactDetails.setFirstName(contactDetailsDto.getFirstName());
        }
        if (contactDetailsDto.getLastName() != null) {
            contactDetails.setLastName(contactDetailsDto.getLastName());
        }
        if (contactDetailsDto.getDob() != null) {
            contactDetails.setDob(contactDetailsDto.getDob());
        }
        if (contactDetailsDto.getMaritalStatus()!=null) {
            contactDetails.setMaritalStatus(contactDetailsDto.getMaritalStatus());
        }

        if (contactDetailsDto.getAddress1() != null) {
            contactDetails.setAddress1(contactDetailsDto.getAddress1());
        }
        if (contactDetailsDto.getAddressLocality() != null) {
            contactDetails.setAddressLocality(contactDetailsDto.getAddressLocality());
        }

        if (contactDetailsDto.getPinCode() != null) {
            contactDetails.setPinCode(contactDetailsDto.getPinCode());
        }

        if (contactDetailsDto.getPrimaryNumber() != null) {
            contactDetails.setPrimaryNumber(contactDetailsDto.getPrimaryNumber());
        }
        if (contactDetailsDto.getDesignation() != null) {
            contactDetails.setDesignation(contactDetailsDto.getDesignation());
        }
        if (contactDetailsDto.getSecondaryNumber() != null) {
            contactDetails.setSecondaryNumber(contactDetailsDto.getSecondaryNumber());
        }
        if (contactDetailsDto.getCompanyName() != null) {
            contactDetails.setCompanyName(contactDetailsDto.getCompanyName());
        }
//        if (contactDetailsDto.getResume() != null) {
//            contactDetails.setResume(contactDetailsDto.getResume());
//        }
        if (contactDetailsDto.getEmailId() != null) {
            contactDetails.setEmailId(contactDetailsDto.getEmailId());
        }
        if (contactDetailsDto.getTotalExperience() != null) {
            contactDetails.setTotalExperience(contactDetailsDto.getTotalExperience());
        }
        if (contactDetailsDto.getIsActive() != null) {
            contactDetails.setIsActive(contactDetailsDto.getIsActive());
        }
        if (contactDetailsDto.getIsInterviewDone() != null) {
            contactDetails.setIsInterviewDone(contactDetailsDto.getIsInterviewDone());
        }
//        if(contactDetailsDto.getImage() != null) {
//            contactDetails.setImage(contactDetailsDto.getImage());
//        }
        if (contactDetailsDto.getCurrentSalary() != null) {
            contactDetails.setCurrentSalary(contactDetailsDto.getCurrentSalary());
        }
        if (contactDetailsDto.getGender() != null) {
            contactDetails.setGender(contactDetailsDto.getGender());
        }
        if (contactDetailsDto.getHighestEducation() != null) {
            contactDetails.setHighestEducation(contactDetailsDto.getHighestEducation());
        }
        if (contactDetailsDto.getHiringType() != null) {
            contactDetails.setHiringType(contactDetailsDto.getHiringType());
        }
        if (contactDetailsDto.getTechRole() != null) {
            contactDetails.setTechRole(contactDetailsDto.getTechRole());
        }
        if (contactDetailsDto.getNoticePeriod() != null) {
            contactDetails.setNoticePeriod(contactDetailsDto.getNoticePeriod());
        }

        // Conditionally fetch and set related entities
        if (contactDetailsDto.getCurrentLocation() != null && contactDetailsDto.getCurrentLocation().getLocationId() != null) {
            MasterLocation currentLocation = masterLocationRepository.findById(contactDetailsDto.getCurrentLocation().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + contactDetailsDto.getCurrentLocation().getLocationId()));
            contactDetails.setCurrentLocation(currentLocation);
        }

        // Save the updated contact
        ContactDetails updatedContact = contactDetailsRepository.save(contactDetails);
        logger.info("Contact updated successfully with id: {}", updatedContact.getContactId());
        return modelMapper.map(updatedContact, ContactDetailsDto.class);
    }


    @Override
    public void deleteContact(Long contactId) {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));
       logger.info("Contact deleted successfully with id: {}", contactDetails.getContactId());
        contactDetailsRepository.delete(contactDetails);
    }

    @Override
    public ContactDetailsDto getContactById(Long contactId) {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));
        logger.info("Contact found successfully with id: {}", contactDetails.getContactId());
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
                case "firstname":
                    contacts = contactDetailsRepository.findByFirstNameContainingIgnoreCase(query);
                    break;
                case "lastname":
                    contacts = contactDetailsRepository.findByLastNameContainingIgnoreCase(query);
                    break;
                case "phone":
//                    Long longQuery = Long.parseLong(query); // Parse query only for phone filter type
                    contacts = contactDetailsRepository.findByPrimaryNumberOrSecondaryNumber(query, query);
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

  @Override
  public void updateResumeField(Long contactId, String value)
      throws IOException, FileNotFoundCustomException {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        String currentResume = contactDetails.getResume();
        if (currentResume != null && !currentResume.isEmpty()) {
            String fullPath= resumePath+currentResume;
            File file = Paths.get(fullPath).toFile();
            if (file.exists()) {
                file.delete();
                logger.info("File deleted successfully");
            } else {
                logger.warn("File not found, cannot delete: {}", fullPath);
//                throw new FileNotFoundCustomException("The file to delete does not exist: " + fullPath);
            }
        }
//        String fullPath= resumePath+currentResume;
//        File file = Paths.get(fullPath).toFile();
//        file.delete();

        contactDetailsRepository.updateResumeByContactId(contactId, value);
     }

  @Override
  public void updateImageField(Long contactId, String value)
      throws IOException, FileNotFoundCustomException {
        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));

        String currentImage = contactDetails.getImage();

      if (currentImage != null && !currentImage.isEmpty()) {
        String fullPath= imagePath+currentImage;
          File file = Paths.get(fullPath).toFile();

          if (file.exists()) {
              file.delete();
              logger.info("File deleted successfully");
          } else {
              logger.warn("File not found, cannot delete: {}", fullPath);
           // throw new FileNotFoundCustomException("The file to delete does not exist: " + fullPath);
          }
      }

        contactDetailsRepository.updateImageByContactId(contactId, value);
    }


    private void validateLocationId(ContactDetailsDto contactDetailsDto) {
        MasterLocationDto currentLocation = contactDetailsDto.getCurrentLocation();

        if (currentLocation == null || contactDetailsDto.getCurrentLocation().getLocationId() == null) {
            logger.error("Location ID must be present in the Location details!");
            throw new IllegalArgumentException("Location ID must be present in the Location details!");
        }

        logger.info("Validating Location ID: {}", contactDetailsDto.getCurrentLocation().getLocationId());
        masterLocationRepository.findById(contactDetailsDto.getCurrentLocation().getLocationId())
                     .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + contactDetailsDto.getCurrentLocation().getLocationId()));

    }

}

