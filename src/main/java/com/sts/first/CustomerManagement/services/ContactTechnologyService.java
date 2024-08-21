package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ContactTechnologyDto;

import java.util.List;

public interface ContactTechnologyService {
    ContactTechnologyDto createContactTechnology(ContactTechnologyDto contactsTechnologyDto);
    ContactTechnologyDto updateContactTechnology(Long id, ContactTechnologyDto contactsTechnologyDto);
    void deleteContactTechnology(Long id);
    ContactTechnologyDto getContactTechnologyById(Long id);
    List<ContactTechnologyDto> getAllContactTechnologies();
}
