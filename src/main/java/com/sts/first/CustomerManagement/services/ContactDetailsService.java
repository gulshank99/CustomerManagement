package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;

import java.util.List;

public interface ContactDetailsService {

    ContactDetailsDto createContact(ContactDetailsDto contactDetailsDto);

    ContactDetailsDto updateContact(Long contactId, ContactDetailsDto contactDetailsDto);

    void deleteContact(Long contactId);

    ContactDetailsDto getContactById(Long contactId);

    List<ContactDetailsDto> getAllContacts();

//    List<ContactDetailsDto> searchContactsByKeyword(String query);

}
