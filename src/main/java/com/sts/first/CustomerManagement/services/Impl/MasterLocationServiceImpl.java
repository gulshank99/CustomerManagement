package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.MasterLocationDto;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.MasterLocationRepository;
import com.sts.first.CustomerManagement.services.MasterLocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterLocationServiceImpl implements MasterLocationService {

    @Autowired
    private MasterLocationRepository masterLocationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MasterLocationDto createLocation(MasterLocationDto locationDto) {
        Long maxId = masterLocationRepository.findMaxId();
        Long newId = maxId + 1;
        locationDto.setLocationId(newId);

        Optional<MasterLocation> existingLocation = masterLocationRepository.findByLocationDetails(locationDto.getLocationDetails());
        if (existingLocation.isPresent()) {
            throw new IllegalArgumentException("Location details already exist.");
        }


        MasterLocation masterLocation = modelMapper.map(locationDto, MasterLocation.class);
        MasterLocation savedLocation = masterLocationRepository.save(masterLocation);
        return modelMapper.map(savedLocation, MasterLocationDto.class);
    }

    @Override
    public MasterLocationDto updateLocation(Long id, MasterLocationDto locationDto) {
        MasterLocation masterLocation = masterLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));

        Optional<MasterLocation> existingLocation = masterLocationRepository.findByLocationDetails(locationDto.getLocationDetails());
        if (existingLocation.isPresent()) {
            throw new IllegalArgumentException("Location details already exist.");
        }

        if (locationDto.getLocationDetails() != null) {
            masterLocation.setLocationDetails(locationDto.getLocationDetails());
        }

        MasterLocation updatedLocation = masterLocationRepository.save(masterLocation);
        return modelMapper.map(updatedLocation, MasterLocationDto.class);
    }

    @Override
    public void deleteLocation(Long id) {
        MasterLocation masterLocation = masterLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        masterLocationRepository.delete(masterLocation);
    }

    @Override
    public MasterLocationDto getLocationById(Long id) {
        MasterLocation masterLocation = masterLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        return modelMapper.map(masterLocation, MasterLocationDto.class);
    }

    @Override
    public List<MasterLocationDto> getAllLocations() {
        return masterLocationRepository.findAll().stream()
                .map(location -> modelMapper.map(location, MasterLocationDto.class))
                .collect(Collectors.toList());
    }
}