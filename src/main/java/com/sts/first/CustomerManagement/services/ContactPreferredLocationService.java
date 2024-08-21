package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ContactPreferredLocationDto;

import java.util.List;

public interface ContactPreferredLocationService {
    ContactPreferredLocationDto createPreferredLocation(ContactPreferredLocationDto preferredLocationDto);
    ContactPreferredLocationDto updatePreferredLocation(Long id, ContactPreferredLocationDto preferredLocationDto);
    void deletePreferredLocation(Long id);
    ContactPreferredLocationDto getPreferredLocationById(Long id);
    List<ContactPreferredLocationDto> getAllPreferredLocations();
}
