package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ClientJobTechDto;

import java.util.List;

public interface ClientJobTechService {
    ClientJobTechDto createJobTech(ClientJobTechDto jobTechDto);
    ClientJobTechDto updateJobTech(Long jobTechId, ClientJobTechDto jobTechDto);
    void deleteJobTech(Long jobTechId);
    ClientJobTechDto getJobTechById(Long jobTechId);
    List<ClientJobTechDto> getAllJobTechs();
}