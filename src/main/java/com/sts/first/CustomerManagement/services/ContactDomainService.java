package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ContactDomainsDto;

import java.util.List;

public interface ContactDomainService {
    ContactDomainsDto createContactDomain(ContactDomainsDto contactDomainsDto);
    ContactDomainsDto updateContactDomain(Long id, ContactDomainsDto contactDomainsDto);
    void deleteContactDomain(Long id);
    ContactDomainsDto getContactDomainById(Long id);

}
