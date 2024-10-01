package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.MasterCompanyDto;

import java.util.List;

public interface MasterCompanyService {

    MasterCompanyDto createCompany(MasterCompanyDto companyDto);
    MasterCompanyDto updateCompany(Long companyId, MasterCompanyDto companyDto);
    void deleteCompany(Long companyId);
    MasterCompanyDto getCompanyById(Long companyId);
    List<MasterCompanyDto> getAllCompanies();
}
