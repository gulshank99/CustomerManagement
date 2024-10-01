package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.MasterClient;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.MasterClientRepository;
import com.sts.first.CustomerManagement.repositories.MasterLocationRepository;
import com.sts.first.CustomerManagement.services.MasterClientService;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterClientServiceImpl implements MasterClientService {

    @Autowired
    private MasterClientRepository masterClientRepository;
    @Autowired
    MasterLocationRepository masterLocationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MasterClientDto createClient(MasterClientDto clientDto) {
        validateLocationId(clientDto);

        Long maxId = masterClientRepository.findMaxId();
        Long newId = maxId + 1;
        clientDto.setClientId(newId);


        Optional<MasterClient> existingClientByName = masterClientRepository.findByClientName(clientDto.getClientName());
        if (existingClientByName.isPresent()) {
            throw new IllegalArgumentException("Client name already exists.");
        }

        // Check if primaryMobile already exists
//        Optional<MasterClient> existingClientByPrimaryMobile = masterClientRepository.findByPrimaryMobile(clientDto.getPrimaryMobile());
//        if (existingClientByPrimaryMobile.isPresent()) {
//            throw new IllegalArgumentException("Primary mobile number already exists.");
//        }

        // Check if secondaryNumber already exists
//        Optional<MasterClient> existingClientBySecondaryNumber = masterClientRepository.findBySecondaryNumber(clientDto.getSecondaryNumber());
//        if (existingClientBySecondaryNumber.isPresent()) {
//            throw new IllegalArgumentException("Secondary number already exists.");
//        }


        MasterClient masterClient = modelMapper.map(clientDto, MasterClient.class);
        MasterClient savedClient = masterClientRepository.save(masterClient);
        return modelMapper.map(savedClient, MasterClientDto.class);
    }

    @Override
    public MasterClientDto updateClient(Long clientId, MasterClientDto clientDto) {
        MasterClient masterClient = masterClientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        Optional<MasterClient> existingClientByName = masterClientRepository.findByClientName(clientDto.getClientName());
        if (existingClientByName.isPresent()) {
            throw new IllegalArgumentException("Client name already exists.");
        }

        // Check if primaryMobile already exists
//        Optional<MasterClient> existingClientByPrimaryMobile = masterClientRepository.findByPrimaryMobile(clientDto.getPrimaryMobile());
//        if (existingClientByPrimaryMobile.isPresent()) {
//            throw new IllegalArgumentException("Primary mobile number already exists.");
//        }

        // Check if secondaryNumber already exists
//        Optional<MasterClient> existingClientBySecondaryNumber = masterClientRepository.findBySecondaryNumber(clientDto.getSecondaryNumber());
//        if (existingClientBySecondaryNumber.isPresent()) {
//            throw new IllegalArgumentException("Secondary number already exists.");
//        }

        if (clientDto.getClientName() != null) {
            masterClient.setClientName(clientDto.getClientName());
        }
//        if (clientDto.getAddress() != null) {
//            masterClient.setAddress(clientDto.getAddress());
//        }
//        if (clientDto.getHrContactPerson() != null) {
//            masterClient.setHrContactPerson(clientDto.getHrContactPerson());
//        }
//        if (clientDto.getTechnicalPerson() != null) {
//            masterClient.setTechnicalPerson(clientDto.getTechnicalPerson());
//        }
//        if (clientDto.getCountryCode() != null) {
//            masterClient.setCountryCode(clientDto.getCountryCode());
//        }
//        if (clientDto.getPrimaryMobile() != null) {
//            masterClient.setPrimaryMobile(clientDto.getPrimaryMobile());
//        }
//        if (clientDto.getSecondaryNumber() != null) {
//            masterClient.setSecondaryNumber(clientDto.getSecondaryNumber());
//        }
//        if (clientDto.getInsertedOn() != null) {
//            masterClient.setInsertedOn(clientDto.getInsertedOn());
//        }

        // Update the location if it's not null
//        if (clientDto.getLocation() != null) {
//            MasterLocation location = masterLocationRepository.findById(clientDto.getLocation().getLocationId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + clientDto.getLocation().getLocationId()));
//            masterClient.setLocation(location);
//        }


        MasterClient updatedClient = masterClientRepository.save(masterClient);
        return modelMapper.map(updatedClient, MasterClientDto.class);
    }

    @Override
    public void deleteClient(Long clientId) {
        MasterClient masterClient = masterClientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        masterClientRepository.delete(masterClient);
    }

    @Override
    public MasterClientDto getClientById(Long clientId) {
        MasterClient masterClient = masterClientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        return modelMapper.map(masterClient, MasterClientDto.class);
    }

    @Override
    public List<MasterClientDto> getAllClients() {
        return masterClientRepository.findAll().stream()
                .map(client -> modelMapper.map(client, MasterClientDto.class))
                .collect(Collectors.toList());
    }

    private void validateLocationId(MasterClientDto masterClientDto) {

//        MasterLocationDto location = masterClientDto.getLocation();
//
//
//        if (location == null || location.getLocationId() == null) {
//            throw new IllegalArgumentException("Location ID must be present in the Location!");
//        }
//
//        if (masterClientDto.getLocation().getLocationId() != null) {
//            masterLocationRepository.findById(masterClientDto.getLocation().getLocationId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " +  masterClientDto.getLocation().getLocationId()));
//        }


    }
}

