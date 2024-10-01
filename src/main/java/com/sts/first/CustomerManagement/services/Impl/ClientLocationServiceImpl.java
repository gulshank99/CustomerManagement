package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ClientLocationDto;
import com.sts.first.CustomerManagement.dtos.MasterClientDto;
import com.sts.first.CustomerManagement.dtos.MasterLocationDto;
import com.sts.first.CustomerManagement.entities.ClientLocation;
import com.sts.first.CustomerManagement.entities.MasterClient;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ClientLocationRepository;
import com.sts.first.CustomerManagement.repositories.MasterClientRepository;
import com.sts.first.CustomerManagement.repositories.MasterLocationRepository;
import com.sts.first.CustomerManagement.services.ClientLocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientLocationServiceImpl implements ClientLocationService {

    @Autowired
    private ClientLocationRepository clientLocationRepository;


    @Autowired
    MasterLocationRepository masterLocationRepository;

    @Autowired
    MasterClientRepository masterClientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientLocationDto createLocation(ClientLocationDto locationDto) {
        validateLocationIdAndClientId(locationDto);
        Long newId = clientLocationRepository.findMaxId() + 1;
        locationDto.setClientLocationId(newId);

//         Check if primaryMobile already exists
        Optional<ClientLocation> existingClientByPrimaryMobile = clientLocationRepository.findByHrMobileNumber(locationDto.getHrMobileNumber());
        if (existingClientByPrimaryMobile.isPresent()) {
            throw new IllegalArgumentException("Mobile number already exists.");
        }

//         Check if secondaryNumber already exists
        Optional<ClientLocation> existingClientBySecondaryNumber = clientLocationRepository.findByCompanyLandline(locationDto.getCompanyLandline());
        if (existingClientBySecondaryNumber.isPresent()) {
            throw new IllegalArgumentException("Landline number already exists.");
        }

        ClientLocation location = modelMapper.map(locationDto, ClientLocation.class);
        ClientLocation savedLocation = clientLocationRepository.save(location);
        return modelMapper.map(savedLocation, ClientLocationDto.class);
    }

    @Override
    public ClientLocationDto updateLocation(Long locationId, ClientLocationDto locationDto) {
        ClientLocation location = clientLocationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationId));

        //         Check if primaryMobile already exists
        Optional<ClientLocation> existingClientByPrimaryMobile = clientLocationRepository.findByHrMobileNumber(locationDto.getHrMobileNumber());
        if (existingClientByPrimaryMobile.isPresent()) {
            throw new IllegalArgumentException("Primary mobile number already exists.");
        }

//         Check if secondaryNumber already exists
        Optional<ClientLocation> existingClientBySecondaryNumber = clientLocationRepository.findByCompanyLandline(locationDto.getCompanyLandline());
        if (existingClientBySecondaryNumber.isPresent()) {
            throw new IllegalArgumentException("Secondary number already exists.");
        }


        if (locationDto.getPincode() != null) {
            location.setPincode(locationDto.getPincode());
        }
        if (locationDto.getAddress1() != null) {
            location.setAddress1(locationDto.getAddress1());
        }
        if (locationDto.getHrContactPerson() != null) {
            location.setHrContactPerson(locationDto.getHrContactPerson());
        }
        if (locationDto.getTechnicalPerson() != null) {
            location.setTechnicalPerson(locationDto.getTechnicalPerson());
        }
        if (locationDto.getHrMobileNumber() != null) {
            location.setHrMobileNumber(locationDto.getHrMobileNumber());
        }
        if (locationDto.getCompanyLandline() != null) {
            location.setCompanyLandline(locationDto.getCompanyLandline());
        }

        // Update the location if it's not null
        if (locationDto.getState() != null) {
            MasterLocation masterLocation = masterLocationRepository.findById(locationDto.getState().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationDto.getState().getLocationId()));
            location.setState(masterLocation);
        }

        if (locationDto.getClient() != null) {
            MasterClient masterClient = masterClientRepository.findById(locationDto.getClient().getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + locationDto.getClient().getClientId()));
            location.setClient(masterClient);
        }

        if (locationDto.getCityId() != null) {
            MasterLocation masterLocation = masterLocationRepository.findById(locationDto.getCityId().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + locationDto.getCityId().getLocationId()));
            location.setCityId(masterLocation);
        }

        ClientLocation updatedLocation = clientLocationRepository.save(location);
        return modelMapper.map(updatedLocation, ClientLocationDto.class);
    }

    @Override
    public void deleteLocation(Long locationId) {
        ClientLocation location = clientLocationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationId));
        clientLocationRepository.delete(location);
    }

    @Override
    public ClientLocationDto getLocationById(Long locationId) {
        ClientLocation location = clientLocationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationId));
        return modelMapper.map(location, ClientLocationDto.class);
    }

    @Override
    public List<ClientLocationDto> getAllLocations() {
        return clientLocationRepository.findAll().stream()
                .map(location -> modelMapper.map(location, ClientLocationDto.class))
                .collect(Collectors.toList());
    }


    private void validateLocationIdAndClientId(ClientLocationDto clientLocationDto) {

        MasterLocationDto state = clientLocationDto.getState();
        MasterClientDto client = clientLocationDto.getClient();
        MasterLocationDto cityId = clientLocationDto.getCityId();

        if (state == null || state.getLocationId() == null) {
            throw new IllegalArgumentException("Location ID must be present in the Location!");
        }

        if (client == null || client.getClientId() == null) {
            throw new IllegalArgumentException("Client ID must be present in the Client!");
        }

        if (cityId == null || cityId.getLocationId() == null) {
            throw new IllegalArgumentException("City ID must be present in the Location!");
        }

        if (clientLocationDto.getState().getLocationId() != null) {
            masterLocationRepository.findById(clientLocationDto.getState().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("State not found with id: " +  clientLocationDto.getState().getLocationId()));
        }

        if (clientLocationDto.getClient().getClientId() != null) {
            masterClientRepository.findById(clientLocationDto.getClient().getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " +  clientLocationDto.getClient().getClientId()));
        }

        if (clientLocationDto.getCityId().getLocationId() != null) {
            masterLocationRepository.findById(clientLocationDto.getCityId().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " +  clientLocationDto.getCityId().getLocationId()));
        }

    }
}
