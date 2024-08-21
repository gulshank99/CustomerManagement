package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.MasterDomainDto;

import java.util.List;

public interface MasterDomainService {
    MasterDomainDto createDomain(MasterDomainDto domainDto);
    MasterDomainDto updateDomain(Long domainId, MasterDomainDto domainDto);
    void deleteDomain(Long domainId);
    MasterDomainDto getDomainById(Long domainId);
    List<MasterDomainDto> getAllDomains();
}
