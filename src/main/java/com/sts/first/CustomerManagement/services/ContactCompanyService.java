package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ContactCompanyDto;

import java.util.List;

public interface ContactCompanyService {

    ContactCompanyDto createContactCompany(ContactCompanyDto contactCompanyDto );

    ContactCompanyDto updateContactCompany(Long id, ContactCompanyDto contactCompanyDto);

    void deleteContactDomain(Long id);

    ContactCompanyDto getContactCompanyById(Long id);

    List<ContactCompanyDto> getAllContactCompanies();
}
