package com.sts.first.CustomerManagement.services.Impl;
import com.sts.first.CustomerManagement.dtos.ContactPreferredLocationDto;
import com.sts.first.CustomerManagement.entities.ContactPreferredLocation;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactPreferredLocationRepository;
import com.sts.first.CustomerManagement.repositories.MasterLocationRepository;
import com.sts.first.CustomerManagement.services.ContactPreferredLocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactPreferredLocationServiceImpl implements ContactPreferredLocationService {

    @Autowired
    private ContactPreferredLocationRepository contactPreferredLocationRepository;
    @Autowired
    private MasterLocationRepository masterLocationRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactPreferredLocationDto createPreferredLocation(ContactPreferredLocationDto preferredLocationDto) {
        ContactPreferredLocation preferredLocation = modelMapper.map(preferredLocationDto, ContactPreferredLocation.class);
        ContactPreferredLocation savedPreferredLocation = contactPreferredLocationRepository.save(preferredLocation);
        return modelMapper.map(savedPreferredLocation, ContactPreferredLocationDto.class);
    }

    @Override
    public ContactPreferredLocationDto updatePreferredLocation(Long id, ContactPreferredLocationDto preferredLocationDto) {
        ContactPreferredLocation preferredLocation = contactPreferredLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preferred location not found with id: " + id));

        // Fetch and set related entities
        if (preferredLocationDto.getLocation() != null && preferredLocationDto.getLocation().getLocationId() != null) {
            MasterLocation masterLocation = masterLocationRepository.findById(preferredLocationDto.getLocation().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + preferredLocationDto.getLocation().getLocationId()));
            preferredLocation.setLocation(masterLocation);
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
}
