package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ContactTechnologyDto;
import com.sts.first.CustomerManagement.entities.ContactTechnology;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactTechnologyRepository;
import com.sts.first.CustomerManagement.services.ContactTechnologyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ContactTechnologyServiceImpl implements ContactTechnologyService {
    @Autowired
    private ContactTechnologyRepository contactsTechnologyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactTechnologyDto createContactTechnology(ContactTechnologyDto contactsTechnologyDto) {
        ContactTechnology contactsTechnology = modelMapper.map(contactsTechnologyDto, ContactTechnology.class);
        ContactTechnology savedContactsTechnology = contactsTechnologyRepository.save(contactsTechnology);
        return modelMapper.map(savedContactsTechnology, ContactTechnologyDto.class);
    }

    @Override
    public ContactTechnologyDto updateContactTechnology(Long id, ContactTechnologyDto contactsTechnologyDto) {
        ContactTechnology contactsTechnology = contactsTechnologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacts technology not found with id: " + id));

        contactsTechnology.setExperience(contactsTechnologyDto.getExperience());
        contactsTechnology.setExpertiseLevel(contactsTechnologyDto.getExpertiseLevel());
        contactsTechnology.setIsPrimary(contactsTechnologyDto.getIsPrimary());
        contactsTechnology.setIsSecondary(contactsTechnologyDto.getIsSecondary());
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
}
