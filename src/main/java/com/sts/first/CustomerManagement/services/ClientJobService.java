package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ClientJobDto;

import java.util.List;

public interface ClientJobService {
    ClientJobDto createJob(ClientJobDto jobDto);
    ClientJobDto updateJob(Long jobId, ClientJobDto jobDto);
    void deleteJob(Long jobId);
    ClientJobDto getJobById(Long jobId);
    List<ClientJobDto> getAllJobs();

    void updateJdField(Long jobId, String jd);
}