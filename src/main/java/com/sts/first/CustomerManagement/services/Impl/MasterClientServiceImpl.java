package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.MasterClientDto;
import com.sts.first.CustomerManagement.entities.MasterClient;
import com.sts.first.CustomerManagement.entities.MasterLocation;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.MasterClientRepository;
import com.sts.first.CustomerManagement.repositories.MasterLocationRepository;
import com.sts.first.CustomerManagement.services.MasterClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        MasterClient masterClient = modelMapper.map(clientDto, MasterClient.class);
        MasterClient savedClient = masterClientRepository.save(masterClient);
        return modelMapper.map(savedClient, MasterClientDto.class);
    }

    @Override
    public MasterClientDto updateClient(Long clientId, MasterClientDto clientDto) {
        MasterClient masterClient = masterClientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        masterClient.setClientName(clientDto.getClientName());
        masterClient.setAddress(clientDto.getAddress());
        masterClient.setHrContactPerson(clientDto.getHrContactPerson());
        masterClient.setTechnicalPerson(clientDto.getTechnicalPerson());
        masterClient.setPrimaryMobile(clientDto.getPrimaryMobile());
        masterClient.setSecondaryNumber(clientDto.getSecondaryNumber());
        masterClient.setInsertedOn(clientDto.getInsertedOn());

        // Update the location if it's not null
        if (clientDto.getLocation() != null) {
            MasterLocation location = masterLocationRepository.findById(clientDto.getLocation().getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + clientDto.getLocation().getLocationId()));
            masterClient.setLocation(location);
        }
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
}

