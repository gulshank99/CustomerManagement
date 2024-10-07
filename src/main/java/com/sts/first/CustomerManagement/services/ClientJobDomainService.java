package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ClientJobDomainDto;
import com.sts.first.CustomerManagement.dtos.ContactDomainsDto;

import java.util.List;

public interface ClientJobDomainService {



    ClientJobDomainDto createClientJobDomain(ClientJobDomainDto  clientJobDomainDto);
    ClientJobDomainDto updateClientJobDomain(Long id, ClientJobDomainDto clientJobDomainDto );
    void deleteClientJobDomain(Long id);
    ClientJobDomainDto getClientJobDomainById(Long id);
    List<ClientJobDomainDto> getAllClientJobDomains();
}
